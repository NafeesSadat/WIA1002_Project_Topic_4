
import java.util.Random;

public class Titans {
    private int height;
    private int legs;
    private final int[] legsNum;
    private int runningSpeed;
    private String walkingPattern;
    private String climbingSkill;
    private final String[] walkingPatternType;
    private final String[] climbingSkillType;
    private int dangerRisk;
    private String titan;
    private final String[] titanType;
    private int titanNumber;

    public Titans() {
        this.legsNum = new int[]{0, 2, 4};
        this.climbingSkillType = new String[]{"Can climb", "Cannot climb"};
        this.walkingPatternType = new String[]{"Not repeated", "Repeated pattern", "Normal pattern"};
        this.titanType = new String[]{"Normal Titan","Abnormal Titan","One of Nine Titans"};
    }
    public void generateTitans(int i){
        Random random = new Random(); 
        this.height = random.nextInt(20)+1;
        this.legs = legsNum[random.nextInt(3)];
        this.runningSpeed = random.nextInt(50);
        this.walkingPattern = walkingPatternType[random.nextInt(3)];
        this.climbingSkill = climbingSkillType[random.nextInt(2)];
        this.titan = titanType[random.nextInt(3)];
        this.titanNumber = i;
    }
    
    public String printNormalTitans(){
        return "Titan " + titanNumber + ":   " + titan + ",    " + "Height:    " + height + "m," + "    " + "Legs Number:   " + legs +
                ",   " + "Walking Pattern:   " + walkingPattern + ",   " +"Climbing Skill:    " + climbingSkill +
                ",    " + "Behavior:   Predictable," + "   " + "Running Speed:    " + runningSpeed + "ms," + "   " +
                "Risk:   " + calculateDangerRisk() + ".";
    }
    
    public String printAbnormalTitans(){
        return "Titan " + titanNumber + ":   " + titan + "   Head Size:    Enlarged," + "    " + "Limbs Size:    Small,   "
                + "Behavior:   Unpredictable," + "   " + "Running Speed:    " +
                runningSpeed + "ms," + "   " + "Risk:   " + calculateDangerRisk() + ".";
    }
    
    public String printNineTitans(){
        return "Titan " + titanNumber + ":  " + titan + "   Risk:  " + calculateDangerRisk() + ".";
    }
            
    public int calculateDangerRisk(){
        int danger = 0;
        if(null == this.titan){
            if(this.getHeight()>20)
                danger += 3;
            if(this.getHeight()<=20 && this.getHeight()>10)
                danger += 2;
            if(this.getHeight()<10)
                danger += 1;
            if(this.getLegs() == 4)
                danger += 3;
            if(this.getLegs() == 2)
                danger += 2;
            if(this.getLegs() == 0)
                danger += 1;
            if(this.getRunningSpeed()>20)
                danger += 3;
            if(this.getRunningSpeed()<=20 && this.getRunningSpeed()>10)
                danger += 2;
            if(this.getRunningSpeed()<10)
                danger += 1;
            if("Not repeated".equals(this.getWalkingPattern()))
                danger += 3;
            if("Repeated pattern".equals(this.getWalkingPattern()))
                danger += 2;
            if("Normal pattern".equals(this.getWalkingPattern()))
                danger += 1;
            this.dangerRisk = danger;
            return danger;
        }
        else switch (this.titan) {
            case "Abnormal Titan" -> {
                this.dangerRisk = 15;
                danger = this.dangerRisk;
                return danger;
            }
            case "One of Nine Titans" -> {
                this.dangerRisk = 19;
                danger = this.dangerRisk;
                return danger;
            }
            default -> {
                if(this.getHeight()>20)
                    danger += 3;
                if(this.getHeight()<=20 && this.getHeight()>10)
                    danger += 2;
                if(this.getHeight()<10)
                    danger += 1;
                if(this.getLegs() == 4)
                    danger += 3;
                if(this.getLegs() == 2)
                    danger += 2;
                if(this.getLegs() == 0)
                    danger += 1;
                if(this.getRunningSpeed()>20)
                    danger += 3;
                if(this.getRunningSpeed()<=20 && this.getRunningSpeed()>10)
                    danger += 2;
                if(this.getRunningSpeed()<10)
                    danger += 1;
                if("Not repeated".equals(this.getWalkingPattern()))
                    danger += 3;
                if("Repeated pattern".equals(this.getWalkingPattern()))
                    danger += 2;
                if("Normal pattern".equals(this.getWalkingPattern()))
                    danger += 1;
                this.dangerRisk = danger;
                return danger;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getLegs() {
        return legs;
    }

    public int getRunningSpeed() {
        return runningSpeed;
    }

    public String getWalkingPattern() {
        return walkingPattern;
    }

    public String getClimbingSkill() {
        return climbingSkill;
    }

    public int getDangerRisk() {
        return dangerRisk;
    }

    public String getTitan() {
        return titan;
    }
    
    public int getTitanNumber() {
        return titanNumber;
    }
    
}
