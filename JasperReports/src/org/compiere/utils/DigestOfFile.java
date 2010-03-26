package org.compiere.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64; 

import sun.security.provider.Sun;


/**
 * @author rlemeill
 * @author Michael Judd BF [ 2736817 ] - remove deprecated BASE64Encoder classes
 *
 */
public class DigestOfFile
{
    /**
     * @param mode ie MD5
     * @throws Exception
     */
    public DigestOfFile(String mode) throws Exception
    {
       digestAgent = MessageDigest.getInstance(mode, "SUN");
    }
    
    /**
     * @param file to hash
     * @return hash
     * @throws Exception
     */
    synchronized public byte[] digestAsByteArray(File file) throws Exception
    {
        digestAgent.reset();
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        for (int bytesRead = 0; (bytesRead = is.read(buffer)) >= 0;)
        {
            digestAgent.update(buffer, 0, bytesRead);
        }
        is.close();
        byte[] digest = digestAgent.digest();
        return digest;
    }
    
    public synchronized byte[] digestAsByteArray(byte[] input) throws Exception
    {
    	digestAgent.reset();
        byte[] digest = digestAgent.digest(input);
        return digest;
    }
    
    /**
     * @author rlemeill
     * @param file
     * @return hash (base64 encoded)
     * @throws Exception
     */
    synchronized public String digestAsBase64(File file) throws Exception
    {
        byte[] digest = digestAsByteArray(file);
        Base64 encoder = new Base64();
        String digestAsBase64 = new String(encoder.encode(digest),"ASCII");
        return digestAsBase64;
    }
    
    /**
     * @param input
     * @return hash (base64 encoded)
     * @throws Exception
     */
    public synchronized String digestAsBase64(byte[] input) throws Exception
    {
    	byte[] digest = digestAsByteArray(input);
    	Base64 encoder = new Base64();
    	String digestAsBase64 = new String(encoder.encode(digest),"ASCII");
        return digestAsBase64;
    }
    
   
    
    //private static final char[] HEX_CHARS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    private MessageDigest digestAgent;
    //private Base64 base64Encoder = new Base64();
    private byte[] buffer = new byte[4096];
    
    /**
     * @author rlemeill
     * @param args file
     */
    public static void main(String[] args)
    {
        try
        {
            java.security.Security.addProvider(new Sun());

            DigestOfFile md5DigestAgent = new DigestOfFile("MD5");
            
            for (int argIndex = 0; argIndex < args.length; argIndex++)
            {
                {
                    String base64Digest = md5DigestAgent.digestAsBase64(new File(args[argIndex]));
                    System.out.println("Base64 MD5 of " + args[argIndex] + " = [" + base64Digest + "]");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
 
    
    /**
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return true if files are identic false otherwise
     */
    public static boolean md5localHashCompare(File file1,File file2)
    {
    	//compute Hash of exisiting and downloaded
    	String hashFile1;
    	String hashFile2;
    	java.security.Security.addProvider(new Sun());
    	try{
    		DigestOfFile md5DigestAgent = new DigestOfFile("MD5");
    		hashFile1 = md5DigestAgent.digestAsBase64(file1);
    		hashFile2 = md5DigestAgent.digestAsBase64(file2);
    		return hashFile1.equals(hashFile2) ; }
    	catch (Exception e)
		{
    		return false;			//if there is an error during comparison return files are difs
		}
    }    
    
    /**
     * @param file
     * @return md5 hash null if file is not found or other error
     */
    public static String GetLocalMD5Hash(File file)
    {
    	String hash;
    	java.security.Security.addProvider(new Sun());
    	try{
    		DigestOfFile md5DigestAgent = new DigestOfFile("MD5");
    		hash = md5DigestAgent.digestAsBase64(file);
    		return hash; }
    	catch (Exception e)
		{
    		return null;			//if there is an error during comparison return files are difs
		}
    }
    
    /**
     * Get md5 hash from byte[]
     * @param input
     * @return mdg hash string
     */
    public static String getMD5Hash(byte[] input)
    {
    	String hash;
    	java.security.Security.addProvider(new Sun());
    	try{
    		DigestOfFile md5DigestAgent = new DigestOfFile("MD5");
    		hash = md5DigestAgent.digestAsBase64(input);
    		return hash; }
    	catch (Exception e)
		{
    		return null;			//if there is an error during comparison return files are difs
		}
    }
}
