import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;



public class MemFile {
	//System.getProperty("user.dir") + "//src//config//testData.properties");
	String curDir = System.getProperty("user.dir");
	
	private static void execute(String command) {
		try {
            // using the Runtime exec method:
            Process p = Runtime.getRuntime().exec(command);
             
            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(p.getInputStream()));
 
            BufferedReader stdError = new BufferedReader(new
                 InputStreamReader(p.getErrorStream()));
            String s = null;
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null)
                System.out.println(s);
            // read the output from the command
            while ((s = stdInput.readLine()) != null)
                System.out.println(s);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void print(String str) {
		System.out.println(str);
	}
	
	public static void createFolder(String path) {
		String cmd = "mkdir " + path;
		execute(cmd);
	}

	public static void createFile(String path) {
		File f = new File(path);
		boolean created = false;
		try {
			created = f.createNewFile();
		} catch (IOException ioe){
			System.err.println(ioe.getMessage());
		}
	}
	
	public static void copy(String from, String to) {
		String cmd = "cp " + from + " " + to;
		execute(cmd);
	}
	
	public static void cleanup(String path) {
		execute("rm -r " + path);
	}
	
	public static void append(String content, String path) {
		try
		{
		    FileWriter fw = new FileWriter(path,true); //the true will append the new data
		    fw.write(content);//append content to the file
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println(ioe.getMessage());
		}
	}
	public static void display(String path) {
		String cmd = "cat " + path;
		execute(cmd);
	}
	public static void list(String dpath) {
		String cmd = "ls -la " + dpath;
		execute(cmd);
	}
	public static void search(String name) {
		String cmd = "find . -name " + name + " -print";
		execute(cmd);
	}
	public static void search(String name, String path) {
		String cmd = "cd " + path; 
		execute(cmd);
		cmd = "find . -name " + name + " -print";
		execute(cmd);
	}
	public static void copyDir(String from, String to) {
		String cmd = "cp -R " + from + " " + to;
		execute(cmd);
	}
	public static void main(String[] args) {
		String curDir =  System.getProperty("user.dir");
		String dpath = curDir + "/testFolder";
		print("create folder: " + dpath);
		createFolder(dpath);	
		String fpath = dpath + "/testFile";
		print("create file: " + fpath);
		createFile(fpath);
		String content = "abc\n";
		print("append : " + content + " to " + fpath);
		append(content, fpath);
		String from = fpath;
		String to = dpath + "/toFile";
		print("copy " + from + " to " + to);
		copy(from, to);
		print("display " + to + ":");
		display(to);
		print("list contents of " + dpath);
		list(dpath);
		String name = "toFile";
		print("search for " + name + " from current dir and down");
		search(name);
		print("search for " + name + " from " + dpath + " and down");
		search(name, dpath);
		String tpath = curDir + "/toFolder";
		print("create to dir " + tpath);
		createFolder(tpath);
		print("copy from dir " + dpath + " to dir " + tpath);
		copyDir(dpath, tpath);
		print("cleanup the dir and their files created so far");
		//cleanup(dpath);
		//cleanup(tpath);
	}

}
