package programtopologicalsorting;

import java.util.ArrayList;
import java.util.List;

public class Course_13519151 {

    public String name;
    public List<String> prerequisites;
    public int semester;
    public int time_begin;
    public int time_finish;

    public Course_13519151(String _name) {
        name = _name;
        prerequisites = new ArrayList<>();
        semester = 0;
    }

    public void AddPrerequisite(String preq) {
        prerequisites.add(preq);
    }
    
    public boolean contains(String preq) {
        return prerequisites.contains(preq);
    }
}
