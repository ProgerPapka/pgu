package webservice.client.IPGU02;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ArchiveUtil {

    private static Logger logger = Logger.getLogger(ArchiveUtil.class);

    public byte[] generateArchive(List<String> nameFiles, File xml) {
        byte[] result = new byte[0];
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(byteOut);
        FileInputStream in = null;
        try {
            in = new FileInputStream(xml.getName());
            zipOut.putNextEntry(new ZipEntry(xml.getName()));
            byte[] buff = new byte[1024];
            int lenn;
            while ((lenn = in.read(buff)) > 0) {
                zipOut.write(buff, 0, lenn);
            }
            zipOut.closeEntry();
            in.close();
            for (String file : nameFiles) {
                in = new FileInputStream(file);
                zipOut.putNextEntry(new ZipEntry(file));
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    zipOut.write(buf, 0, len);
                }
                zipOut.closeEntry();
                in.close();
            }
            result = byteOut.toByteArray();
        }catch (FileNotFoundException e) {
            logger.error("File didn't find", e);
        } catch (IOException e) {
            logger.error("IO error", e);
        } finally {
            try {
                byteOut.close();
            } catch (IOException e) {
                logger.error("IO error фе at closing", e);
            }
            try {
                zipOut.close();
            } catch (IOException e) {
                logger.error("IO error фе at closing", e);
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("IO error фе at closing", e);
                }
            }
        }
        return result;
    }

}
