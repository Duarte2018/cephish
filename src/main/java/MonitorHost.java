import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.net.InetAddress;

import static java.lang.System.out;

public class MonitorHost {

    final static int PORT = 9090;
    final static String HOSTNAME = "monitor1";
    final static int OSDID = 1;
    static InetAddress hostAddress;

    public static ClusterMap clusterMap;
    public static SystemMachine thisMachine;

    public static void main(String[] args) throws IOException, InterruptedException {
        hostAddress = InetAddress.getLocalHost();
        clusterMap = new ClusterMap();

        thisMachine = new SystemMachine(HOSTNAME, hostAddress.getHostAddress(), "MDS", true, true );

        clusterMap.addMachine(thisMachine);

        Server server = ServerBuilder
                .forPort(PORT)
                .addService(new MapVersionImpl()).build();

        out.println(server.toString());

        server.start();
        out.println("server started. Port: "+PORT+" "+server.toString());
        server.awaitTermination();

    }
}
