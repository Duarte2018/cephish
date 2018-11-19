

public class SystemMachine {
    String machineName;
    String machineIp;
    String type;
    boolean up;
    boolean updated;
    double mapVersion;

    public SystemMachine(String name, String type){
        this.machineName = name;
        this.machineIp = null;
        this.type = type;
        up = true;
        updated = false;
        this.mapVersion = 0.0;
    }
}
