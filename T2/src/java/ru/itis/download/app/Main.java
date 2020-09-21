package ru.itis.download.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.itis.download.utils.Task;
import ru.itis.download.utils.Args;
import com.beust.jcommander.JCommander;
/**problem : for git ';' is end of command.In my program ';' is "LLL" */
/** for exampl java -jar download.jar --mode=one-thread --files=https://zveri.guru/images/232854/sobaka-schenok-medvezhonok.jpgLLLht tps://i.pinimg.com/236x/dd/4c/8c/dd4c8c22d3a9bb02068a0d13162b1e74--white-bunnies-white-rabbits.jpg --folder=C:\\Users\\
User\\Pictures\\forApp*/


public class Main {
    public static void main(String[] argv) {
       		
		Args args = new Args();
		JCommander.newBuilder()
					.addObject(args)
					.build()
					.parse(argv);
		int count =(args.mode.equals("one-thread"))? 1 : args.count;
		ExecutorService executorService = Executors.newFixedThreadPool(count);
		for(String str: args.files){
			executorService.submit(new Task(str,args.folder));
			
		}
      		
		executorService.shutdown();

    }
}
