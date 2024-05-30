import clientCommandService.ClientCommandService;
import clientCommandService.CollectionInput;
import clientCommandService.OpenUniqueFile;
import consoleService.ConsoleService;
import exceptions.ScriptRecursionException;
import network.CommandRequest;
import network.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UDPClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 4096;

    private static final Logger logger = LogManager.getLogger(UDPClient.class);

    private ConsoleService consoleService;
    private CollectionInput collectionInput;
    private ClientCommandService commandService;
    private DatagramChannel datagramChannel;
    private OpenUniqueFile openUniqueFile;
    SocketAddress serverAddress = new InetSocketAddress(SERVER_ADDRESS, SERVER_PORT);

    public void run() {
        try {
            consoleService = new ConsoleService(new Scanner(System.in));
            collectionInput = new CollectionInput(consoleService);
            commandService = new ClientCommandService(collectionInput);
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            while (true) {
                openUniqueFile = new OpenUniqueFile();
                System.out.print("> ");
                try {
                    String[] com = consoleService.parseCommand();
                    processUserPrompt(com);

                } catch (Exception e) {
                    logger.error(e.getStackTrace());
                }

            }
        }catch (IOException e){
            logger.error(e.getStackTrace());
        }
    }

    private void processUserPrompt(String[] args) throws IOException, ClassNotFoundException {
        if(args.length==0){
            return;
        }
        if (args[0].equalsIgnoreCase("execute_script")) {
            executeScript(args[1]);
        }
        else if (args[0].equalsIgnoreCase("exit")){
            System.exit(0);
        }else {
            CommandRequest request = commandService.getRequest(args);
            if(request!=null) {
                sendAndReceive(request);
            }
        }
    }

    private void sendAndReceive(CommandRequest message) throws ClassNotFoundException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

            byte[] messageBytes = byteArrayOutputStream.toByteArray();
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            buffer.put(messageBytes);
            buffer.flip();

            datagramChannel.send(buffer, serverAddress);

            buffer.clear();

            SocketAddress responseAddress;
            buffer = ByteBuffer.allocate(BUFFER_SIZE);

            while (true) {
                responseAddress = datagramChannel.receive(buffer);
                if (responseAddress != null) {
                    break;
                }
                logger.info("Waiting for answer from server...");
                Thread.sleep(100);
            }

            buffer.flip();
            byte[] receivedData = new byte[buffer.remaining()];
            buffer.get(receivedData);

            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedData);
                 ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {

                CommandResponse response = (CommandResponse) objectInputStream.readObject();
                logger.info(response.getMessage());
            }

        } catch (IOException | InterruptedException e) {
            logger.error(e.getStackTrace());
        }
    }




    private void executeScript(String path) throws ClassNotFoundException {
        if (path.isBlank()) {
            logger.error("Invalid arguments");

        } else {
            try {

                Path pathToScript = Paths.get(path);

                consoleService.setScanner(new Scanner(pathToScript));
                Scanner scriptScanner = consoleService.getScanner();

                if (!scriptScanner.hasNext()) throw new NoSuchElementException();


                do {
                    var command = "";
                    var arguments = "";
                    String[] input = consoleService.parseCommand();
                    if (input.length == 2) {
                        arguments = input[1];
                    }
                    command = input[0];

                    while (scriptScanner.hasNextLine() && command.isEmpty()) {
                        input = consoleService.parseCommand();
                        if (input.length == 2) {
                            arguments = input[1];
                        }
                        command = input[0];

                    }

                    if (command.equalsIgnoreCase("execute_script")) {
                        try {
                            if (openUniqueFile.check(arguments)) {
                                executeScript(arguments);
                            } else throw new ScriptRecursionException("");

                        } catch (ScriptRecursionException e) {
                            logger.error(e.getMessage());
                        }

                    } else {
                        processUserPrompt(input);
                    }

                } while (scriptScanner.hasNextLine());

                consoleService.setScanner(new Scanner(System.in));

            } catch (FileNotFoundException e) {
                logger.error("File {} is not found", path);
            } catch (NoSuchElementException e) {
                logger.error("File {} is empty", path);
            } catch (IllegalStateException e) {
                logger.error(e.getMessage());
            } catch (SecurityException e) {
                logger.error("No permission to read the file {}", path);
            } catch (IOException | InvalidPathException e) {
                logger.error("Invalid path!");
            }
        }


    }
}
