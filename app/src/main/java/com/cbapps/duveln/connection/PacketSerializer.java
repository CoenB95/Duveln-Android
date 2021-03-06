package com.cbapps.duveln.connection;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author CoenB95
 */

public class PacketSerializer {
	private ExecutorService service;
	//public readonly JsonSerializerSettings JsonSettings;
	private boolean open;

	//public event Action OnConnectionClosed;

	private InputStream inputStream;
	private OutputStream outputStream;
	private Gson serializer;

	public PacketSerializer(InputStream inputStream, OutputStream outputStream) {
		service = Executors.newCachedThreadPool();
		this.inputStream = inputStream;
		this.outputStream = outputStream;
//				Open = true;
//				serializer = JsonSerializer.CreateDefault();
//				JsonSettings = new JsonSerializerSettings()
//				{
//					TypeNameHandling = TypeNameHandling.Objects,
//					SerializationBinder = this
//				};
	}

	public void notifyConnectionClosed() {
		synchronized (this) {
			if (open) {
				open = false;
				//OnConnectionClosed?.Invoke();
			}
		}
	}

	public Future<ClientSidePacket> readClientSidePacket() {
		return service.submit(() -> {
			try {
				String json = readPacket(inputStream);
				serializer.fromJson(json, ClientSidePacket.class);
			} catch (IOException e) {
				notifyConnectionClosed();
			}
			return null;
		});
	}

	private String readPacket(InputStream stream) throws IOException {
		//4 bytes make 32 bits.
		ByteBuffer buffer = ByteBuffer.wrap(read(stream, 4));
		int msgLength = buffer.getInt();
		return new String(read(stream, msgLength), Charset.forName("UTF-8"));
	}

	private byte[] read(InputStream stream, int byteCount) throws IOException {
		byte[] result = new byte[byteCount];

		int bytesRead = 0;
		while (bytesRead < byteCount) {
			bytesRead += stream.read(result, bytesRead, byteCount - bytesRead);
		}
		return result;
	}

	public Future startClientSidePacketReader(OnPacketReceivedListener onPacketReceived) {
		return service.submit(() -> {
			while (open) {
				try {
					String json = readPacket(inputStream);
					ClientSidePacket packet = serializer.fromJson(json, ClientSidePacket.class);
					if (packet != null)
						onPacketReceived.onPacketReceived(packet);
				} catch (IOException e) {
					notifyConnectionClosed();
				}
			}
		});
	}

//	public Task StartServerSidePacketReader(Action<IServerPacket> onPacketReceived) {
//		return Task.Factory.StartNew(() = >
//				{
//		while (Open) {
//			try {
//				string json = ReadPacket(stream);
//				IServerPacket packet = JsonConvert.DeserializeObject < IServerPacket > (json, JsonSettings)
//				;
//				if (packet != null)
//					onPacketReceived ?.Invoke(packet);
//			} catch (IOException) {
//				NotifyConnectionClosed();
//			}
//		}
//			});
//	}
//
//	private void Write(NetworkStream stream, byte[] bytes) {
//		stream.Write(bytes, 0, bytes.Length);
//	}
//
//	private void WritePacket(NetworkStream stream, string packetJson) {
//		Write(stream, BitConverter.GetBytes(packetJson.Length));
//		Write(stream, Encoding.UTF8.GetBytes(packetJson));
//	}
//
//	public Task WritePacketAsync(IClientPacket packet) {
//		return Task.Factory.StartNew(() = >
//				{
//						lock(stream)
//						{
//		try {
//			WritePacket(stream, JsonConvert.SerializeObject(packet, packet.GetType(), JsonSettings));
//		} catch (IOException) {
//			NotifyConnectionClosed();
//		}
//				}
//			});
//	}
//
//	public Task WritePacketAsync(IServerPacket packet) {
//		return Task.Factory.StartNew(() = >
//				{
//						lock(stream)
//						{
//		try {
//			WritePacket(stream, JsonConvert.SerializeObject(packet, packet.GetType(), JsonSettings));
//		} catch (IOException) {
//			NotifyConnectionClosed();
//		}
//				}
//			});
//	}

//	public Type BindToType(string assemblyName, string typeName) {
//		try {
//			Type type = Type.GetType(typeName);
//			if (!(typeof(IServerPacket).IsAssignableFrom(type) ||
//					typeof(IClientPacket).IsAssignableFrom(type) ||
//					typeof(IData).IsAssignableFrom(type) ||
//					typeof(IEnumerable).IsAssignableFrom(type))) {
//				if (typeof(Color).FullName == typeName)
//					return typeof(Color);
//				else
//					return null;
//			}
//			return type;
//		} catch (Exception) {
//			return null;
//		}
//	}
//
//	public void BindToName(Type serializedType, out string assemblyName, out string typeName) {
//		assemblyName = "CsharpEindopdracht";
//		typeName = typeof(IServerPacket).IsAssignableFrom(serializedType) ||
//				typeof(IClientPacket).IsAssignableFrom(serializedType) ||
//				typeof(IData).IsAssignableFrom(serializedType) ||
//				typeof(IEnumerable).IsAssignableFrom(serializedType) ||
//				serializedType == typeof(Color) ?
//				serializedType.AssemblyQualifiedName : null;
//	}

	@FunctionalInterface
	public interface OnPacketReceivedListener {
		void onPacketReceived(ClientSidePacket packet);
	}
}