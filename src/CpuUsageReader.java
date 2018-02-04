import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class CpuUsageReader {
	
	private int cpuUsage = 0;
	
	public CpuUsageReader(){
		
		String cpuUsagePath = "/proc/stat";
		String temporaryString;
		float temporaryFloat = 0;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(cpuUsagePath));
			try {
				
				temporaryString = br.readLine();
				String[] array1 = temporaryString.split(" ");
				for(int i=2; i<array1.length; i++){
					temporaryFloat += Integer.parseInt(array1[i]);
				}
				temporaryFloat = (1 - (Integer.parseInt(array1[5]))/(temporaryFloat))*100;
				br.close();
				
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				System.out.println("Could not convert to integer.");
				
			} catch (IOException e) {
				
				e.printStackTrace();
				System.out.println("Issue while reading the file.");
				
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("File not found.");
		}
		this.cpuUsage = (int)(temporaryFloat);
		
	}

	public int getCpuUsage(){return this.cpuUsage;}
}