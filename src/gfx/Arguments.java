package gfx;

public class Arguments{
    private final String[] args;
    private String currentArg;
    private int i;
    private boolean incrementNext = false;
    public Arguments(String[] args){
        this.args = args;
    }

    private boolean canApply(String arg){
        if(currentArg.equals(arg) && i+1<args.length){
            incrementNext = true;
            return true;
        }
        return false;
    }
    public void parseAndApplyArguments(){
        for (i = 0; i < args.length; i++){
            currentArg = args[i];
            if(canApply("-c")){
                try{
                    CircleAnimation.CIRCLES = Integer.parseInt(args[i + 1]);
                }catch (NumberFormatException numFExc){
                    System.out.println("Couldn't parse circle argument");
                }
            }else if (canApply("-r")){
                try{
                    CircleAnimation.RADIUS = Integer.parseInt(args[i + 1]);
                }catch (NumberFormatException numFExc){
                    System.out.println("Couldn't parse radius argument");
                }
            }else if (canApply("-w")){
                try{
                    EntryPoint.displayWidth = Integer.parseInt(args[i + 1]);
                }catch (NumberFormatException numFExc){
                    System.out.println("Couldn't parse width argument");
                }
            }else if (canApply("-h")){
                try{
                    EntryPoint.displayHeight = Integer.parseInt(args[i + 1]);
                }catch (NumberFormatException numFExc){
                    System.out.println("Couldn't parse height argument");
                }
            }else if (currentArg.equals("-draw")){
                CircleAnimation.DRAW_LINES = true;
                incrementNext = false;
            }

            if(incrementNext){
                incrementNext = false;
                i++;
            }
        }
    }
}
