package ru.itis.download.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ru.itis.download.utils.Task;
import ru.itis.utils.Args;
import com.beust.jcommander.JCommander;
/**problem : for git ';' is end of command.In my program ';' is "LLL" */
/** java -jar download.jar --mode=m --count=2 --files=https://zveri.guru/images/232854/sobaka-schenok-medvezhonok.jpgLLLhttps://i.pinimg.com/236x/dd/4c/8c/dd4c8c22d3a9bb02068a0d13162b1e74--white-bunnies-white-rab bits.jpgLLLhttp://risovat-legko.com/wp-content/uploads/malenkie-kartinki-dlja-srisovki-1.jpg --folder=C:\\Users\\User\\Pictures\\forApp*/


public class Main {
    public static void main(String[] argv) {
       		
		Args args = new Args();
		JCommander.newBuilder()
					.addObject(args)
					.build()
					.parse(argv);
		if(args.mode.equals("one-thread")){
			for(String str: args.files){
				(new Task(str,args.folder)).run();
			}
			
		}
		else{
			ExecutorService executorService = Executors.newFixedThreadPool(args.count);
			for(String str: args.files){
				executorService.submit(new Task(str,args.folder));
			
			}
      		
			executorService.shutdown();
		}

    }
}