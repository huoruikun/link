package com.link.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Properties {

	private static Map<String,String> config=new HashMap<String, String>();

	static{
		init_config();
		//by hrk 2011-5-16
		new Thread(){
			public void run(){
				while(true){
					try{						
						init_config();
						//SysConfig.load();
						Thread.currentThread().sleep(600000L);//10 minutes ---test 20s
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		
	}
	
	private static void init_config(){
		java.util.Properties props=new java.util.Properties();
		try{
			InputStream ins=Thread.currentThread().getContextClassLoader().getResourceAsStream("resources.properties");
			if (ins!=null){
				props.load(ins);
				ins.close();
				for (Entry<Object, Object> ent:props.entrySet()){
					config.put((String)ent.getKey(), (String)ent.getValue());
				}
			}
			ins=Thread.currentThread().getContextClassLoader().getResourceAsStream("gatewap.properties");
			if (ins!=null){
				props.load(ins);
				ins.close();
				for (Entry<Object, Object> ent:props.entrySet()){
					config.put((String)ent.getKey(), (String)ent.getValue());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public static String get(String key){
		return config.get(key);
	}
}
