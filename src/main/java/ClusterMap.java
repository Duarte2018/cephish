import java.util.List;

import static java.lang.System.*;

public class ClusterMap {
    //cluster map singleton?
    private static ClusterMap single_instance = null;
    int cluster_active_machines;
    Double mapVersion;
    List<ClusterMap> clusterMapHistory;
    List<SystemMachine> monitorList;
    List<SystemMachine> osdList;
    List<PlacementGroup> placeGroupList;

    public ClusterMap(){
        this.cluster_active_machines = 0;
        this.mapVersion = 0.01;
        this.monitorList = null;
        this.osdList = null;
        this.placeGroupList = null;
    }

    /*public static ClusterMap getSingle_instance() {
        if (single_instance == null) {
            single_instance = new ClusterMap();
        }
        return single_instance;
    }*/

    public int getCluster_active_machines() {
        return cluster_active_machines;
    }

    public Double getMapVersion() {
        return mapVersion;
    }

    public List<ClusterMap> getClusterMapHistory() {
        return clusterMapHistory;
    }

    public List<SystemMachine> getMonitorList() {
        return monitorList;
    }

    public List<SystemMachine> getOsdList() {
        return osdList;
    }

    public List<PlacementGroup> getPlaceGroupList() {
        return placeGroupList;
    }

    public void increment_version(){
        updateClusterMapHistory(this);
        this.mapVersion += 0.01;
    }

    public void updateClusterMapHistory(ClusterMap cm){
        this.getClusterMapHistory().add(cm);
    }

    public void addMachine(SystemMachine machine){
        if ("OSD".equals(machine.getType())) {
            if (machine.isUp() && machine.isUpdated()) {
                this.osdList.add(machine);
                this.cluster_active_machines++;
            }
        }
        if ("MDS".equals(machine.getType())){
            if (machine.isUp() && machine.isUpdated()) {
                this.monitorList.add(machine);
                this.cluster_active_machines++;
            }
        }else{
            out.println("Invalid machine type: "+machine.getType());
        }
    }

    public void removeMachine(SystemMachine machine){
        if(!machine.isUp()){
            if("OSD".equals(machine.getType()) && this.osdList.contains(machine)){
                this.osdList.remove(machine);
            }
            if("MDS".equals(machine.getType()) && this.monitorList.contains(machine)){
                this.monitorList.remove(machine);
            }
        }
    }
}
