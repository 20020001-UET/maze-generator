package client.print;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class Print {
    public static void print(ArrayList<ArrayList<Integer>> data, String filename) {
        try {
            RandomAccessFile stream = new RandomAccessFile(filename, "rw");
            stream.setLength(0);
            FileChannel channel = stream.getChannel();

            StringBuilder fileContent = new StringBuilder();
            int height = data.size();
            int width = data.get(height-1).size();

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    switch (data.get(i).get(j)) {
                        case 1, 2 -> {
                            fileContent.append("1");
                        }
                        case 0, 3 -> {
                            fileContent.append("0");
                        }
                        case 5, 6, 7 -> {
                            fileContent.append(data.get(i).get(j)-3);
                        }
                        case -1 -> {
                            fileContent.append(" ");
                        }
                    }
                    //fileContent.append(" ");
                }
                fileContent.append("\n");
            }

            byte[] strBytes = fileContent.toString().getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);

            buffer.put(strBytes);
            buffer.flip();
            channel.write(buffer);

            stream.close();
            channel.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
