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


//    public static void separAndConnectFile(File f1, int b) throws IOException {
//        InputStream in = new FileInputStream(f1);
//        OutputStream out1 = new FileOutputStream("C:\\dir1\\testStrPart1.txt");
//        OutputStream out2 = new FileOutputStream("C:\\dir1\\testStrPart2.txt");
//        OutputStream out3 = new FileOutputStream("C:\\dir1\\testStrPartsTogether.txt");
//        InputStream in1 = new FileInputStream("C:\\dir1\\testStrPart1.txt");
//        InputStream in2 = new FileInputStream("C:\\dir1\\testStrPart2.txt");
//        int count = 0;
//        int count2 = 0;
//        byte[] buff = new byte[1024];
//        while ((count = in.read(buff)) != -1) {
//            if (b <= 1024)
//                out1.write(buff, 0, );
//            else out1.write(buff, 1, count);
//        }

//        byte[] buff1 = new byte[b];
//        byte[] buff2 = new byte[1024];
//        in.read(buff2);
////        System.out.println("coun1="+count1);
//        out1.write(buff2, 0, b);
//        in1.read(buff2);
//        out3.write(buff2);
//        while ((count2 = in.read(buff2)) != -1)
//            out2.write(buff2, b, count2+b);
//        while ((count2 = in2.read(buff2)) != -1)
//            out3.write(buff2, b, count2+b);
//        closeQuietly(in);
//        closeQuietly(in1);
//        closeQuietly(in2);
//        OutputStream out3 = new FileOutputStream("C:\\dir1\\testStrPartsTogether.txt");
//        InputStream in1 = new FileInputStream("C:\\dir1\\testStrPart1.txt");
//        InputStream in2 = new FileInputStream("C:\\dir1\\testStrPart2.txt");
//        count1 = in.read(buff1);
//        out1.write(buff1, 0, count1);
//        while ((count2=in.read(buff2))!=-1)
//            out3.write(buff2, 1);
//
//    }

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
//            separAndConnectFile(file1, 256);
        } catch (IOException e) {
            throw new IOException("Error with reading or writing file" + e);

        }


    }
}
