import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    // 设置该服务监听的端口
    private final static int TCP_PORT = 9090;

 	   public static void main(String[] args) throws IOException {
		// 创建 server socket，关于该类的详细介绍参考
		//     https://docs.oracle.com/javase/7/docs/api/
        	ServerSocket ss = new ServerSocket(TCP_PORT);
		// 监听到请求之后，创建socket
	        Socket socket = ss.accept();

		// 获取请求
		//     从socke创建InputStream
		//     创建InputStreamReader
		//     创建BufferReader
	        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//     从Buffer中获取HTTP请求的内容，并在终端显示出来
	        String buffer = null;
	        while ((buffer = br.readLine()) != null && !buffer.equals("")) {
	            System.out.println(buffer);
	        }

		// 返回信息
		//     通过socket创建OutputStream
		//     创建OutputStreamWriter
		//     创建BufferWriter
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		//     在Buffer中写入待返回的信息
	        bw.write("HTTP/1.1 200 OK\n");
	        bw.write("Content-Type: text/html; charset=UTF-8\n\n");
	        bw.write(
			"<html>\n"
			+ "<head>\n" 
			+ "	<title>first page</title>\n"
	                + "</head>\n"
			+ "<body>\n" 
			+ "	<h1>Hello Web Server!</h1>\n"
	                + "</body>\n" 
			+ "</html>\n"
		);
		//     将Buffer中的数据 *发送* 出去，之后清空Buffer
	        bw.flush();
		// 关闭BufferWriter
	        bw.close();
		// 关闭BufferReader
	        br.close();
		// 关闭socket
	        socket.close();
		// 关闭ServerSocket
	        ss.close();
	    }
}
