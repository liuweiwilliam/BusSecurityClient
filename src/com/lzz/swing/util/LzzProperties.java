package com.lzz.swing.util;

public class LzzProperties {
	private static String ServerUrl = null;
	private static String ServerUrl2 = null;
	private static String ServerPort = null;
	private static String VpnUrl = null;
	private static String VpnPort = null;
	private static String Uname = null;
	private static String Pwd = null;
	private static String RememberPwd = null;
	
	public static String getDataRequestUrl(){
		return LzzProperties.getServerUrl() + ":" + LzzProperties.getServerPort() + "/BusSecurityMonitorSys";
	}
	
	public static String getServerUrl() {
		if(ServerUrl==null){
			ServerUrl = getProperties("ServerUrl");
		}
		return ServerUrl;
	}
	
	public static String getServerUrl2() {
		if(ServerUrl2==null){
			ServerUrl2 = getProperties("ServerUrl2");
		}
		return ServerUrl2;
	}
	
	public static String getServerPort() {
		if(ServerPort==null){
			ServerPort = getProperties("ServerPort");
		}
		return ServerPort;
	}
	
	public static String getVpnPort() {
		if(VpnPort==null){
			VpnPort = getProperties("VpnPort");
		}
		return VpnPort;
	}
	
	public static String getVpnUrl() {
		if(VpnUrl==null){
			VpnUrl = getProperties("VpnUrl");
		}
		return VpnUrl;
	}
	
	public static String getUname() {
		if(Uname==null){
			Uname = getProperties("Uname");
		}
		return Uname;
	}
	
	public static String getPwd() {
		if(Pwd==null){
			Pwd = getProperties("PWD");
		}
		return Pwd;
	}
	
	public static String getRememberPwd() {
		if(RememberPwd==null){
			RememberPwd = getProperties("RememberPwd");
		}
		return RememberPwd;
	}

	private static String getProperties(String string) {
		return LzzOprDataFromFile.readParDataFromFile(string);
	}
	
	public static boolean saveProperties(String name, String value){
		return LzzOprDataFromFile.writeParDataToFile(name, value);
	}

	/**
	 * 将server_url写入配置文件
	 * @param par
	 */
	public static void saveServerUrl(String par) {
		if(null==par) return;
		ServerUrl = par;
		saveProperties("ServerUrl", par);
	}
	
	/**
	 * 将server_url2写入配置文件
	 * @param par
	 */
	public static void saveServerUrl2(String par) {
		if(null==par) return;
		ServerUrl2 = par;
		saveProperties("ServerUrl2", par);
	}
	
	/**
	 * 将server_port写入配置文件
	 * @param par
	 */
	public static void saveServerPort(String par) {
		if(null==par) return;
		ServerPort = par;
		saveProperties("ServerPort", par);
	}
	
	/**
	 * 将vpn_url写入配置文件
	 * @param par
	 */
	public static void saveVpnUrl(String par) {
		if(null==par) return;
		VpnUrl = par;
		saveProperties("VpnUrl", par);
	}
	
	/**
	 * 将vpn_port写入配置文件
	 * @param par
	 */
	public static void saveVpnPort(String par) {
		if(null==par) return;
		VpnPort = par;
		saveProperties("VpnPort", par);
	}
	
	/**
	 * 将Uname写入配置文件
	 * @param par
	 */
	public static void saveUname(String par) {
		if(null==par) return;
		Uname = par;
		saveProperties("Uname", par);
	}
	
	/**
	 * 将Pwd写入配置文件
	 * @param par
	 */
	public static void savePwd(String par) {
		if(null==par) return;
		Pwd = par;
		saveProperties("PWD", par);
	}
	/**
	 * 将RememberPwd写入配置文件
	 * @param par
	 */
	public static void saveRememberPwd(String par) {
		if(null==par) return;
		RememberPwd = par;
		saveProperties("RememberPwd", par);
	}
}
