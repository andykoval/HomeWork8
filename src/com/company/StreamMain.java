package com.company;


import java.io.*;
import java.lang.*;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by andy on 14.11.2017.
 */
public class StreamMain {
    //
//    public static void readFullyByByte(java.io.InputStream in) throws IOException {
//        while (true) {
//            int OneByte = in.read();
//            if (OneByte != -1)
//                System.out.print((char) OneByte);
//            else {
//                System.out.print("\n" + "end");
//                break;
//            }
//        }
//    }
    //    public static void readFullyByByteArray(java.io.InputStream in) throws IOException {
//        byte[] buff = new byte[1024];
//        int count = 0;
//        while (true) {
//            count = in.read(buff);
//            if (count != -1)
//                System.out.println("count= " + count + ", buff= "
//                        + Arrays.toString(buff) + ", str= "
//                        + new String(buff, 0, count, "UTF8"));
//            else break;
//        }
//    }
    public static void readFullyByByte(java.io.InputStream in) throws IOException {
        int OneByte;
        while ((OneByte = in.read()) != -1)
            System.out.print((char) OneByte);
    }

    public static void readFullyByByteArray(java.io.InputStream in) throws IOException {
        byte[] buff = new byte[1024];
        int count = 0;
        while ((count = in.read(buff)) != -1) {
            System.out.println("count= " + count + ", buff= "
                    + Arrays.toString(buff) + ", str= "
                    + new String(buff, 0, count, "UTF8"));
        }
    }

    public static void closeQuietly(java.io.InputStream in) throws IOException {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ignore) {
            }
        }
    }

    public static void closeAndFlushQuietly(java.io.OutputStream out) throws IOException {
        if (out != null) {
            try {
                out.flush();
            } catch (IOException ignore) {
            }
            try {
                out.close();
            } catch (IOException ignore) {
            }
        }
    }

    public static void readAndWriteByByteArray(File f1, File f2) throws IOException {
        InputStream in = new FileInputStream(f1);
        OutputStream out = new FileOutputStream(f2);
        byte[] buff = new byte[1024];
        int count = 0;
        while ((count = in.read(buff)) != -1)
            out.write(buff, 0, count);
        closeQuietly(in);
        closeAndFlushQuietly(out);
    }


    public static void separAndPlusFile(File f1, int lenb) throws IOException {
        int bufSize = 512;
        InputStream in = new BufferedInputStream(new FileInputStream(f1), bufSize);
        OutputStream out1 = new BufferedOutputStream(new FileOutputStream("C:\\dir1\\testStrPart1.txt"), bufSize);
        OutputStream out2 = new BufferedOutputStream(new FileOutputStream("C:\\dir1\\testStrPart2.txt"), bufSize);
        int oneByte, count = 0;
        while ((oneByte = in.read()) != -1) {
            if (lenb == 0) break;
            count++;
            out1.write(oneByte);
            if (count == lenb) break;
        }
        while ((oneByte = in.read()) != -1)
            out2.write(oneByte);
        closeQuietly(in);
        closeAndFlushQuietly(out1);
        closeAndFlushQuietly(out2);
        InputStream in2 = new BufferedInputStream(new FileInputStream("C:\\dir1\\testStrPart1.txt"), bufSize);
        InputStream in3 = new BufferedInputStream(new FileInputStream("C:\\dir1\\testStrPart2.txt"), bufSize);
        OutputStream out3 = new BufferedOutputStream(new FileOutputStream("C:\\dir1\\testStrPart1Part2.txt"), bufSize);
        while ((oneByte = in2.read()) != -1)
            out3.write(oneByte);
        while ((oneByte = in3.read()) != -1)
            out3.write(oneByte);
        closeQuietly(in2);
        closeQuietly(in3);
        closeAndFlushQuietly(out3);
    }
//       }

    public static void main(String[] args) throws IOException {
//        java.io.InputStream inFile = new FileInputStream("C:\\dir1\\testStr.txt");
//        readFullyByByte(inFile);
//        System.out.println("\n\n\n");
//
//        java.io.InputStream inUrl = new URL("http://google.com").openStream();
//        readFullyByByte(inUrl);
//        System.out.println("\n\n\n");
//
//        java.io.InputStream inByte = new ByteArrayInputStream(new byte[]{34,77,65,66,79});
//        readFullyByByte(inByte);
//        System.out.println("\n\n\n");
        String fileName = "C:\\dir1\\testStr.txt";
        java.io.InputStream inFile = null;
        try {
            inFile = new java.io.FileInputStream(fileName);
//            readFullyByByte(inFile);
            readFullyByByteArray(inFile);
        } catch (IOException e) {
            throw new IOException("Exception then open and read file" + fileName, e);
        } finally {
            closeQuietly(inFile);
        }

        File file1 = new File("C:\\dir1\\testStr.txt");
        File file2 = new File("C:\\dir1\\testStrCopy.txt");
        try {
            readAndWriteByByteArray(file1, file2);
            separAndPlusFile(file1, 5000);
            System.out.println("fileSize = " + file2.length());
        } catch (IOException e) {
            throw new IOException("Error with reading or writing file" + e);
        }

    }
}
