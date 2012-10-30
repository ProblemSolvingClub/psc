import java.util.*;

public class G {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for(int caseNum = 0; caseNum < caseCount; ++caseNum) {
            int peopleCount = in.nextInt();
            int observCount = in.nextInt();
            ArrayList<Person> people = new ArrayList<Person>(peopleCount);
            ArrayList<String> names = new ArrayList<String>(peopleCount);
            for (int i = 0; i < peopleCount; ++i) {
                String name = in.next();
                names.add(name);
                int x = in.nextInt();
                int y = in.nextInt();
                people.add(new Person(i, x, y));
            }
            ArrayList<Observation> sureObservs = new ArrayList<Observation>(observCount);
            ArrayList<Observation> unsureObservs = new ArrayList<Observation>(observCount);
            for (int i = 0; i < observCount; ++i) {
                String S1name = in.next();
                in.next(); //heard
                String S2name = in.next();
                in.next(); //firing
                in.next(); //before
                String S3name = in.next();
                Person S1 = people.get(names.indexOf(S1name));
                Person S2 = people.get(names.indexOf(S2name));
                Person S3 = people.get(names.indexOf(S3name));
                Observation newObserv = new Observation(S1, S2, S3);
                if (newObserv.isSure) {
                    sureObservs.add(newObserv);
                    S1.AddSureObservation(newObserv);
                }
                else {
                    unsureObservs.add(newObserv);
                    S1.AddUnsureObservation(newObserv);
                }
            }
        }
    }

    public boolean HasCycle(ArrayList<Observation> observs, int peopleCount) {
        boolean[] people = new boolean[peopleCount];
        for (int i = 0; i < peopleCount; ++i)
            people[i] = false;

    }

    public static class Person {
        public int name;
        public int x;
        public int y;
        public ArrayList<Observation> sureObservs;
        public ArrayList<Observation> unsureObservs;
        public Person(int name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.sureObservs = new ArrayList<Observation>();
            this.unsureObservs = new ArrayList<Observation>();
        }
        public AddSureObservation(Observation o) {
            this.sureObservs.add(o);
        }
        public AddUnsureObservation(Observation o) {
            this.unsureObservs.add(o);
        }
        public double DistFrom(Person o) {
            double xDist = (double)Math.abs(o.x-this.x);
            double yDist = (double)Math.abs(o.y-this.y);
            return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2));
        }
    }

    public static class Observation {
        public Person S1;
        public Person S2;
        public Person S3;
        boolean isSure;
        public Observation(Person S1, Person S2, Person S3) {
            this.S1 = S1; 
            this.S2 = S2; 
            this.S3 = S3; 
            if (Fequal(this.S1.DistFrom(this.S2), this.S1.DistFrom(this.S3))) {
                this.isSure = true;
            }
            else if (this.S1.DistFrom(this.S3) < this.S1.DistFrom(this.S2)) {
                this.isSure = true;
            }
            else {
                this.isSure = false;
            }
        }
    }

    boolean Fequal(double a, double b) {
        double diff = Math.abs(a-b); 
        return (diff < 10e-7) ;
    }
}
