package AddBooks;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ElasticSearchAddBooksClass {
    private static String booksPath = "C:/Users/avata/Desktop/Książki";

    public static void main(String[] args) {
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "elasticsearch").build();

            TransportClient client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

            File folder = new File(booksPath);
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < 1; i++) {
                File file = listOfFiles[i];
                String fileName = file.getName();
                fileName = fileName.replace("_", " ");
                fileName = fileName.replace(".txt", "");

                String fileString = booksPath + "/" + fileName;
                Path filePath = Path.of(fileString);

                String content = Files.readString(filePath);
                Map<String, Object> json = new HashMap<>();
                json.put("title", fileName);
                json.put("content", content);

                System.out.println((i+1) + ". " + fileName);
                IndexResponse indexResponse = client.prepareIndex("database", "ksiazki", Integer.toString(i+1))
                        .setSource(json, XContentType.JSON)
                        .get();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
