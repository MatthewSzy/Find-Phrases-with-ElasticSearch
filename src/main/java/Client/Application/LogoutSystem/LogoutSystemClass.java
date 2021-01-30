package Client.Application.LogoutSystem;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LogoutSystemClass {
    final private Socket clientSocket;
    final private String userLogin;

    public LogoutSystemClass(Socket clientSocket, String userLogin) {
        this.clientSocket = clientSocket;
        this.userLogin = userLogin;
    }

    public String sendData() throws IOException { return send(); }
    private String send() throws IOException {
        DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
        dataSender.writeUTF("LOGOUT");
        dataSender.writeUTF(userLogin);
        dataSender.flush();
        return "Logout";
    }
}
