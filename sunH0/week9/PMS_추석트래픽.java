import java.util.ArrayList;
import java.util.List;

public class  PMS_추석트래픽{

  private List<Integer> starts;
  private List<Integer> startTimes;
  private List<Integer> endTimes;


  public int solution(String[] lines) {
    timeToMilliseconds(lines, starts, startTimes, endTimes);
    int max = 0;

    for (int startSection : starts) {
      int endSection = startSection + 1000;
      max = Math.max(max, getProcessCount(startSection, endSection));
    }

    return max;
  }

  private int getProcessCount(int startSection, int endSection) {
    int count = 0;
    for (int i = 0; i < startTimes.size(); i++) {
      if (isPartOfSection(startSection, endSection, startTimes.get(i), endTimes.get(i))) {
        count++;
      }
    }
    return count;
  }

  private boolean isPartOfSection(int startSection, int endSection, int startTime, int endTime) {
    return (startTime >= startSection && startTime < endSection)
        || (endTime >= startSection && endTime < endSection)
        || (startTime <= startSection && endTime >= endSection);
  }

  private void timeToMilliseconds(String[] lines, List<Integer> starts, List<Integer> startTimes,
      List<Integer> endTimes) {
    for (String line : lines) {
      String[] log = line.split(" ");
      int endTime = getSeconds(log[1]);
      int processTime = (int) (Double.parseDouble(log[2].replace("s", "")) * 1000);
      int startTime = endTime - processTime + 1;
      startTimes.add(startTime);
      endTimes.add(endTime);
    }
    starts.addAll(startTimes);
    starts.addAll(endTimes);
  }

  private int getSeconds(String time) {
    String[] split = time.split(":");
    return (Integer.parseInt(split[0]) * 60 * 60 * 1000)
        + (Integer.parseInt(split[1]) * 60 * 1000)
        + (int) (Double.parseDouble(split[2]) * 1000);
  }
}