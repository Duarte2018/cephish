import SD2018.cephish.*;
import SD2018.cephish.SystemMachine;
import io.grpc.stub.StreamObserver;

import static java.lang.System.out;

public class MapVersionImpl extends MapVersionGrpc.MapVersionImplBase {

    ClusterMap clusterMap = new ClusterMap();

    public void IsUpdated (MapVersionRequest request, StreamObserver<MapVersionResponse> mapVersionResponseObserver){
        out.println("Service beginning for map update request: IsUpdated");

        //passar o mapa para a response
        //construir osdlist e mdslist
        OsdList.Builder osdList = buildOsdList(clusterMap);

        MdsList.Builder mdsList = buildMdsList(clusterMap);

        MapVersionResponse clusterMapUpdate = MapVersionResponse.newBuilder()
                .setClusterMapVersion(clusterMap.getMapVersion())
                .setOsdList(osdList)
                .setMdsList(mdsList)
                .build();

        out.println(clusterMapUpdate.toString());

        mapVersionResponseObserver.onNext(clusterMapUpdate);
        mapVersionResponseObserver.onCompleted();
    }

    public OsdList.Builder buildOsdList(ClusterMap cm){
        OsdList.Builder osdList = OsdList.newBuilder();
        for (int i=0; i<cm.osdList.size();i++){
            osdList.addOsdHost(SystemMachine.newBuilder()
                    .setMachineName(cm.osdList.get(i).getMachineName())
                    .setMachineIp(cm.osdList.get(i).getMachineIp().toString())
                    .setType(cm.osdList.get(i).getType())
                    .build());
        }
        osdList.build();
        return osdList;
    }

    public MdsList.Builder buildMdsList(ClusterMap cm){
        MdsList.Builder mdsList = MdsList.newBuilder();
        for (int i=0; i<cm.monitorList.size();i++){
            mdsList.addMsdHost(SystemMachine.newBuilder()
                    .setMachineName(cm.monitorList.get(i).getMachineName())
                    .setMachineIp(cm.monitorList.get(i).getMachineIp().toString())
                    .setType(cm.monitorList.get(i).getType())
                    .build());
        }
        mdsList.build();
        return mdsList;
    }
}
