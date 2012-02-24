package cpw.mods.compactsolars;

import net.minecraft.src.ModLoader;

public enum ServerClientProxy {
	CLIENT("cpw.mods.compactsolars.client.ClientProxy"),
	SERVER("cpw.mods.compactsolars.server.ServerProxy");
	
	private String className;
	private ServerClientProxy(String proxyClassName) {
		className=proxyClassName;
	}
	
	private IProxy buildProxy() {
		try {
			return (IProxy) Class.forName(className).newInstance();
		} catch (Exception e) {
			ModLoader.getLogger().severe("A fatal error has occured initializing CompactSolars");
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}
	public static IProxy getProxy() {
		try {
			ModLoader.class.getMethod("getMinecraftInstance");
		} catch (SecurityException e) {
			// UNPOSSIBLE
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			return SERVER.buildProxy();
		}
		return CLIENT.buildProxy();
	}

}
