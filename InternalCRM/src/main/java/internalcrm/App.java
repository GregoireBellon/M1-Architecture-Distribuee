/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package internalcrm;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import internalcrm.services.LeadServiceImpl;
import internalcrm.services.thrift.impl.LeadService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static LeadServiceImpl handler;

    public static LeadService.Processor<LeadServiceImpl> processor;

    public static void main(String[] args) {
        try {
            handler = LeadServiceImpl.getInstance();
            processor = new LeadService.Processor<LeadServiceImpl>(handler);

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(LeadService.Processor<LeadServiceImpl> processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new
            // TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {

            log.error("Erreur lors ");

            e.printStackTrace();

        }
    }
}