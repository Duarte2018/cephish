import java.net.InetAddress;

public class SystemMachine {
    private String machineName;
    private InetAddress machineIp;
    private String type;
    private boolean up;
    private boolean updated;
    private double mapVersion;

    public SystemMachine(String machineName, InetAddress machineIp, String type, boolean up, boolean updated) {
        this.machineName = machineName;
        this.machineIp = machineIp;
        this.type = type;
        this.up = up;
        this.updated = updated;
        this.mapVersion = 0.0;
    }

    public String getMachineName() {
        return machineName;
    }

    public InetAddress getMachineIp() {
        return machineIp;
    }

    public String getType() {
        return type;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isUpdated() {
        return updated;
    }

    public double getMapVersion() {
        return mapVersion;
    }
}
