package tr.com.innova.payflex.campaignCriteria.drlGenerator.helpers;

import java.io.*;

public class FileHelper {
	public static void writeFile(String drlText, String filename){
		BufferedWriter writer = null;
		try
		{
			String folderPath = "D:\\Eclipse\\eclipse-workspace\\CampaignCriteriaDrlGenerator\\target\\classes\\drlFiles." + filename + "\\";
			new File(folderPath).mkdirs();
			String path = folderPath + filename +".drl";
			writer = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(path), "UTF-8"
					));			
		    writer.write(drlText);
		}
		catch ( IOException ignored)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException ignored)
		    {
		    }
		}
	}
	
	public static void deleteFile(String filename){
		String folderPath = "D:\\Eclipse\\eclipse-workspace\\CampaignCriteriaDrlGenerator\\target\\classes\\drlFiles." + filename + "\\";
		new File(folderPath + filename +".drl").delete();
		new File(folderPath).delete();
	}	
}
