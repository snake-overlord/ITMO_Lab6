import DMS.DMS_Configuration;
import commandService.CommandService;
import commandService.userCommands.Save;
import network.CommandRequest;
import network.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class UDPServer {
    private static final int SERVER_PORT = 9876;
    private static final int BUFFER_SIZE = 4096;

    private static final Logger logger = LogManager.getLogger(UDPServer.class);

    public static void run() {
        DatagramChannel datagramChannel = null;
        CommandService commandService = new CommandService();

        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.bind(new InetSocketAddress(SERVER_PORT));
            datagramChannel.configureBlocking(false);

            logger.info("Server started at port {}. Type 'save' to save the collection. \n>", SERVER_PORT);

            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

            Thread consoleThread = new Thread(() -> {
                Scanner scan = new Scanner(System.in);
                while (true) {
                    if (scan.hasNextLine()) {
                        String command = scan.nextLine().trim();
                        if (command.equals("save")) {
                            Save save = new Save(DMS_Configuration.build());
                            save.execute();
                            logger.info("saved!");
                        } else {
                            logger.error("Unknown server command. Type 'save' to save the collection.");
                        }
                        System.out.print("\n>");
                    }
                }
            });
            consoleThread.setDaemon(true);
            consoleThread.start();

            while (true) {
                SocketAddress clientAddress = datagramChannel.receive(buffer);

                if (clientAddress != null) {
                    logger.info("Client address: {}", clientAddress);
                    try {
                        buffer.flip();
                        byte[] receivedData = new byte[buffer.remaining()];
                        buffer.get(receivedData);
                        buffer.clear();

                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedData);
                        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                        CommandRequest commandRequest = (CommandRequest) objectInputStream.readObject();
                        logger.info("Received command: {}", commandRequest.getName());
                        Thread.sleep(5000);

                        CommandResponse responseMessage = commandService.executeCommand(commandRequest);

                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(responseMessage);
                        objectOutputStream.flush();
                        byte[] responseBytes = byteArrayOutputStream.toByteArray();

                        ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);
                        datagramChannel.send(responseBuffer, clientAddress);
                        logger.info("Response sent to client: {}", responseMessage.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (datagramChannel != null) {
                try {
                    datagramChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}

