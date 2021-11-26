
package timetrackingexam.bll.utilities;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.bll.facade.ITimeTrackBLL;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;


        
/**
 * This class is responsible for doing all the maths behind the diagrammes
 * @author math2
 */
public class StatisticsCalculator {
    
    private int lcIndex = 0;
    private ObservableList<Task> tasks;
    private ITimeTrackBLL dBFacade;
    private Project currentProject;
    private ObservableList<TaskTime> times;
    

    public StatisticsCalculator(Project p) {
        currentProject = p;
        dBFacade = new TimeTrackBLLFacade();
        tasks = dBFacade.getTasks(currentProject);
    }
    
    public StatisticsCalculator() {
        dBFacade = new TimeTrackBLLFacade();
        tasks = dBFacade.getTasks(currentProject);
    }
    
    
    
    /**
     * This method calculates the growth in time, over time. each time it is called
     * the index goes up and it adds the integer to the sum of time growth
     * @return a long representing the incrementing time
     */
    public XYChart.Series timeUsedPerDay(){
        
        setTime();
        XYChart.Series series = new XYChart.Series<>();
        
        for (TaskTime time : times) {
            
            series.getData().add(new XYChart.Data(
                    time.getDate().toString(),
                    time.getHours()));
        }
        return series;
    }
    
    public XYChart.Series timeUsedPerWeek(){
        
        XYChart.Series series = timeUsedPerDay();
        List<XYChart.Data> datas = series.getData();
        
        XYChart.Series weekSeries = new XYChart.Series();
        
        WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
        int weekNumber = LocalDate.parse(
                datas.get(0).getXValue().toString()).get(weekFields.weekOfWeekBasedYear());
        int total = 0;
        
        for (int i = 0; i < datas.size(); i++) {
            int newWeekNumber = LocalDate.parse(datas.get(i).getXValue().toString()).get(weekFields.weekOfWeekBasedYear());
            
            if(newWeekNumber != weekNumber || i == datas.size() -1 ){
                weekSeries.getData().add(new XYChart.Data<>(
                        "Week " + weekNumber,
                        total));
                weekNumber = newWeekNumber;
                total = 0;
            }
            
            else if(newWeekNumber == weekNumber){
                total += Integer.parseInt(datas.get(i).getYValue().toString());
                datas.get(i).setXValue("Week " + newWeekNumber);
                weekNumber = newWeekNumber;
            }
            
        }
        return weekSeries;
    }
    
    public XYChart.Series timeUsedPerMonth(){
        setTime();
        XYChart.Series series = timeUsedPerDay();
        List<XYChart.Data> datas = series.getData();
        
        XYChart.Series monthSeries = new XYChart.Series();
        
        LocalDate ld1 = LocalDate.parse(datas.get(0).getXValue().toString());
        String month = ld1.getMonth().toString();
        int total = 0;
        
        for (int i = 0; i < datas.size(); i++) {
            LocalDate ld2 = LocalDate.parse(datas.get(i).getXValue().toString());
            String newMonth = ld2.getMonth().toString();
            
            if(!newMonth.equals(month) || i == datas.size() -1 ){
                monthSeries.getData().add(new XYChart.Data<>(month,
                        total));
                month = newMonth;
                total = 0;
            }
            
            else if(newMonth.equals(month)){
                total += Integer.parseInt(datas.get(i).getYValue().toString());
                datas.get(i).setXValue(newMonth);
                month = newMonth;
            }
            
        }
        return monthSeries;
    }
    
    public XYChart.Series intervalSeries(LocalDate ld1, LocalDate ld2){
        XYChart.Series intervalSeries = new XYChart.Series<>();
        
        List<XYChart.Data> datas = timeUsedPerDay().getData();
        
        for (XYChart.Data data : datas) {
            LocalDate ld = LocalDate.parse(data.getXValue().toString());
            if (ld.getDayOfYear() >= ld1.getDayOfYear() && ld.getDayOfYear() <= ld2.getDayOfYear()) {
                intervalSeries.getData().add(data);
            }
        }
        return intervalSeries;
    }
    
      
    
    public ObservableList<PieChart.Data> getHoursPerTaskUsed(){
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Task task : tasks) {
            pieChartData.add(new PieChart.Data(task.getName(), totalHours(task)));
            
        }
        return pieChartData;
    }
    
    public int totalHours(Task t){
        
        ObservableList<TaskTime> times = FXCollections.observableArrayList();
        int totalHour = 0;
        times.addAll(dBFacade.getTime(t));
        
        for (TaskTime time : times) {
            totalHour += time.getHours();
        }
        
        return totalHour;
    }
    
    private void setTime(){
        times = FXCollections.observableArrayList();
        
        XYChart.Series series = new XYChart.Series();
        for (Task task : tasks) {
            times.addAll(dBFacade.getTime(task));
        }
        
        times.sort((arg, arg1) -> {
            return (arg.getDate().getDayOfYear() - arg1.getDate().getDayOfYear());
        });
    }
}
