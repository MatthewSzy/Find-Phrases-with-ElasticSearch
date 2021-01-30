package Server.ElasticSearchConnection;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchConnectionClass {
    public ElasticSearchConnectionClass() {}

    public TransportClient createConnection() throws UnknownHostException { return create(); }
    private TransportClient create() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        return client;
    }
}
