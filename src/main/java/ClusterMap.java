import java.util.List;

public class ClusterMap {
    //cluster map singleton?
    private static ClusterMap single_instance = null;
    int active_machines;
    Double mapVersion;
    List<SystemMachine> clusterMap;

    private ClusterMap(){
        this.active_machines = 0;
        this.mapVersion = 0.01;
    }

    public static ClusterMap getSingle_instance() {
        if (single_instance == null) {
            single_instance = new ClusterMap();
        }
        return single_instance;
    }

    public void increment_version(){
        this.mapVersion += 0.01;
    }

    public void addMachine(SystemMachine machine){
        if (machine.up && machine.updated){
            this.clusterMap.add(machine);
        }
    }

    public void removeMachine(SystemMachine machine){
        if (!machine.up && this.clusterMap.contains(machine)){
            this.clusterMap.remove(machine);
        }
    }
}
