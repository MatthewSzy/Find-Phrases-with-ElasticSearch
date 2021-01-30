package Server.SearchPhrase.SearchSystem;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class SearchSystemClass {
    final private Socket clientSocket;
    final private TransportClient client;

    public SearchSystemClass(Socket clientSocket, TransportClient client) {
        this.clientSocket = clientSocket;
        this.client = client;
    }

    public void searchPhrase() throws IOException { search(); }
    private void search() throws IOException {
        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String phrase = dataReceiver.readUTF();
        SearchResponse response = client.prepareSearch("books")
                .setTypes("book")
                .setQuery(QueryBuilders.matchPhraseQuery("content", phrase).slop(5))
                .get();

        SearchHit[] hits = response.getHits().getHits();
        sendData(hits);
    }

    public void sendData(SearchHit[] hits) throws IOException { send(hits); }
    private void send(SearchHit[] hits) throws IOException {
        DataOutputStream dataSend = new DataOutputStream(clientSocket.getOutputStream());
        for (SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            dataSend.writeUTF(sourceAsMap.get("title").toString());
            dataSend.writeUTF(sourceAsMap.get("author").toString());
            dataSend.flush();
        }
        for (int i = hits.length; i < 10; i++) {
            dataSend.writeUTF("---/---");
            dataSend.writeUTF("---/---");
            dataSend.flush();
        }
    }
}
