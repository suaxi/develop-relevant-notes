package com.sw.chat.protocol;

import com.sw.chat.config.Config;
import com.sw.chat.message.LoginRequestMessage;
import com.sw.chat.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author suaxi
 * @date 2026/07/23 22:55
 */
public class SerializerTest {
    public static void main(String[] args) {
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler();
        EmbeddedChannel channel = new EmbeddedChannel(LOGGING_HANDLER, MESSAGE_CODEC, LOGGING_HANDLER);
        LoginRequestMessage loginRequestMessage = new LoginRequestMessage("liubo", "123");
        // object ---> byte
        // channel.writeOutbound(loginRequestMessage);

        // JDK Serializer
        // 22:58:21.511 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] WRITE: 215B
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 01 00 00 00 00 00 00 ff 00 00 00 c7 |................|
        // |00000010| ac ed 00 05 73 72 00 27 63 6f 6d 2e 73 77 2e 63 |....sr.'com.sw.c|
        // |00000020| 68 61 74 2e 6d 65 73 73 61 67 65 2e 4c 6f 67 69 |hat.message.Logi|
        // |00000030| 6e 52 65 71 75 65 73 74 4d 65 73 73 61 67 65 81 |nRequestMessage.|
        // |00000040| 67 c8 60 bd c6 4b b2 02 00 02 4c 00 08 70 61 73 |g.`..K....L..pas|
        // |00000050| 73 77 6f 72 64 74 00 12 4c 6a 61 76 61 2f 6c 61 |swordt..Ljava/la|
        // |00000060| 6e 67 2f 53 74 72 69 6e 67 3b 4c 00 08 75 73 65 |ng/String;L..use|
        // |00000070| 72 6e 61 6d 65 71 00 7e 00 01 78 72 00 1b 63 6f |rnameq.~..xr..co|
        // |00000080| 6d 2e 73 77 2e 63 68 61 74 2e 6d 65 73 73 61 67 |m.sw.chat.messag|
        // |00000090| 65 2e 4d 65 73 73 61 67 65 72 9b f3 82 41 12 0e |e.Messager...A..|
        // |000000a0| 4d 02 00 02 49 00 0b 6d 65 73 73 61 67 65 54 79 |M...I..messageTy|
        // |000000b0| 70 65 49 00 0a 73 65 71 75 65 6e 63 65 49 64 78 |peI..sequenceIdx|
        // |000000c0| 70 00 00 00 00 00 00 00 00 74 00 03 31 32 33 74 |p........t..123t|
        // |000000d0| 00 05 6c 69 75 62 6f                            |..liubo         |
        // +--------+-------------------------------------------------+----------------+

        // Json
        // 23:05:04.978 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] WRITE: 84B
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 01 01 00 00 00 00 00 ff 00 00 00 44 |...............D|
        // |00000010| 7b 22 75 73 65 72 6e 61 6d 65 22 3a 22 6c 69 75 |{"username":"liu|
        // |00000020| 62 6f 22 2c 22 70 61 73 73 77 6f 72 64 22 3a 22 |bo","password":"|
        // |00000030| 31 32 33 22 2c 22 73 65 71 75 65 6e 63 65 49 64 |123","sequenceId|
        // |00000040| 22 3a 30 2c 22 6d 65 73 73 61 67 65 54 79 70 65 |":0,"messageType|
        // |00000050| 22 3a 30 7d                                     |":0}            |
        // +--------+-------------------------------------------------+----------------+

        // byte ---> object
        ByteBuf bf = msgToByteBuf(loginRequestMessage);
        channel.writeInbound(bf);

        // JDK Serializer
        // 23:07:25.305 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: 215B
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 01 00 00 00 00 00 00 ff 00 00 00 c7 |................|
        // |00000010| ac ed 00 05 73 72 00 27 63 6f 6d 2e 73 77 2e 63 |....sr.'com.sw.c|
        // |00000020| 68 61 74 2e 6d 65 73 73 61 67 65 2e 4c 6f 67 69 |hat.message.Logi|
        // |00000030| 6e 52 65 71 75 65 73 74 4d 65 73 73 61 67 65 81 |nRequestMessage.|
        // |00000040| 67 c8 60 bd c6 4b b2 02 00 02 4c 00 08 70 61 73 |g.`..K....L..pas|
        // |00000050| 73 77 6f 72 64 74 00 12 4c 6a 61 76 61 2f 6c 61 |swordt..Ljava/la|
        // |00000060| 6e 67 2f 53 74 72 69 6e 67 3b 4c 00 08 75 73 65 |ng/String;L..use|
        // |00000070| 72 6e 61 6d 65 71 00 7e 00 01 78 72 00 1b 63 6f |rnameq.~..xr..co|
        // |00000080| 6d 2e 73 77 2e 63 68 61 74 2e 6d 65 73 73 61 67 |m.sw.chat.messag|
        // |00000090| 65 2e 4d 65 73 73 61 67 65 72 9b f3 82 41 12 0e |e.Messager...A..|
        // |000000a0| 4d 02 00 02 49 00 0b 6d 65 73 73 61 67 65 54 79 |M...I..messageTy|
        // |000000b0| 70 65 49 00 0a 73 65 71 75 65 6e 63 65 49 64 78 |peI..sequenceIdx|
        // |000000c0| 70 00 00 00 00 00 00 00 00 74 00 03 31 32 33 74 |p........t..123t|
        // |000000d0| 00 05 6c 69 75 62 6f                            |..liubo         |
        // +--------+-------------------------------------------------+----------------+
        // 23:07:25.309 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: LoginRequestMessage(super=Message(sequenceId=0, messageType=0), username=liubo, password=123)

        // Json
        // 23:08:47.272 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: 84B
        //         +-------------------------------------------------+
        //         |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
        // +--------+-------------------------------------------------+----------------+
        // |00000000| 01 02 03 04 01 01 00 00 00 00 00 ff 00 00 00 44 |...............D|
        // |00000010| 7b 22 75 73 65 72 6e 61 6d 65 22 3a 22 6c 69 75 |{"username":"liu|
        // |00000020| 62 6f 22 2c 22 70 61 73 73 77 6f 72 64 22 3a 22 |bo","password":"|
        // |00000030| 31 32 33 22 2c 22 73 65 71 75 65 6e 63 65 49 64 |123","sequenceId|
        // |00000040| 22 3a 30 2c 22 6d 65 73 73 61 67 65 54 79 70 65 |":0,"messageType|
        // |00000050| 22 3a 30 7d                                     |":0}            |
        // +--------+-------------------------------------------------+----------------+
        // 23:08:47.275 [main] DEBUG io.netty.handler.logging.LoggingHandler - [id: 0xembedded, L:embedded - R:embedded] READ: LoginRequestMessage(super=Message(sequenceId=0, messageType=0), username=liubo, password=123)
    }

    public static ByteBuf msgToByteBuf(Message msg) {
        int algorithm = Config.getSerializerAlgorithm().ordinal();
        ByteBuf bf = ByteBufAllocator.DEFAULT.buffer();
        bf.writeBytes(new byte[]{1, 2, 3, 4});
        bf.writeByte(1);
        bf.writeByte(algorithm);
        bf.writeByte(msg.getMessageType());
        bf.writeInt(msg.getSequenceId());
        bf.writeByte(0xff);
        byte[] bytes = Serializer.Algorithm.values()[algorithm].serialize(msg);
        bf.writeInt(bytes.length);
        bf.writeBytes(bytes);
        return bf;
    }
}
