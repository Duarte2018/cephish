import SD2018.cephish.MapVersionGrpc;
import SD2018.cephish.MapVersionRequest;
import SD2018.cephish.MapVersionResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;

import static java.lang.System.out;

public class ObjectStorageHost extends ObjectStorageDevice {

    final static int OSDPORT = 9095;
    final static String OSDHOST = "localhost";
    String MONITORHOST = "localhost";
    final static int MONITORPORT = 9090;
    final static String HOSTNAME = "OSD1";
    final static int OSDID = 1;

    public ClusterMap clusterMap;

    public ObjectStorageHost(String HOSTNAME, String filesystem, int OSDID) {
        super(HOSTNAME, filesystem, OSDID);
    }

    public void updateClusterMap(){
        if(this.clusterMap.mapVersion==0.0){
            //rpc call to storageService
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8088)
                    .usePlaintext()
                    .build();

            MapVersionGrpc.MapVersionBlockingStub stub = MapVersionGrpc.newBlockingStub(channel);

            MapVersionResponse mapUpdateResponse = stub.isUpdated(MapVersionRequest.newBuilder()
                    .setClusterMapVersion(this.clusterMap.getMapVersion())
                    .setHostIp(OSDHOST)
                    .setPort(OSDPORT)
                    .setType("OSD").build());

            out.println(mapUpdateResponse.toString());

            channel.shutdown();
            out.println(channel.getState(false).toString());
        }
    }


    public void main(String[] args) throws IOException, InterruptedException {

        if (this.clusterMap == null){
            this.clusterMap = new ClusterMap();
        }

        out.println("Updating cluster Map");
        updateClusterMap();

    }
}
