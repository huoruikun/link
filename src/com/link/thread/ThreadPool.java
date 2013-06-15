package com.link.thread;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 需要在独立的线程中进行的操作可以直接add runnable到该pool
 * @author ruikunh
 *
 */
public class ThreadPool {
	private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	private Map<String, Schadule> schadule_map = new ConcurrentHashMap<String, Schadule>();
	private final ExecutorService pool;
	private final ExecutorService schadule_pool;
	private boolean server_running = false;
	
	private static ThreadPool instance = new ThreadPool();
	
	public static ThreadPool getInstance(){
		return instance;
	}
	
	private ThreadPool(){
		pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
		schadule_pool = Executors.newScheduledThreadPool(1);
	}
	
	public void init(){
		server_running = true;
		((ScheduledExecutorService) schadule_pool).scheduleAtFixedRate(new MyTimer(), 20, 20, TimeUnit.MINUTES);
		new Thread(){
			public void run(){
				System.out.println("ThreadPool init ...");
				exec();
				init();
			}
		}.start();
	}
	
	public void destroy(){
		server_running = false;		
		((ScheduledExecutorService) schadule_pool).shutdown();
	}
	
	public boolean offer(Runnable obj){
		return queue.offer(obj);
	}
	
	public void schadule(String key, Schadule s){
		schadule_map.put(key, s);
	}
	
	public void cancelSchadule(String key){
		schadule_map.remove(key);
	}
	
	private void exec(){
		while(true){
			try{
				if(!server_running){
					break;
				}
				Runnable obj = queue.poll(5, TimeUnit.SECONDS);
				if(obj!=null){
					pool.execute(obj);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	class MyTimer implements Runnable {

		@Override
		public void run() {
			System.out.println("MyTimer is running...");
			for(String key : schadule_map.keySet()){
				try{
					Schadule s = schadule_map.get(key);
					s.run();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
}
