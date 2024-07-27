public class Event {
    private String name;
    private int startTime;
    private int endTime;

    public Event(String name, int startTime, int endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Event{name='" + name + "', startTime=" + startTime + ", endTime=" + endTime + '}';
    }
}