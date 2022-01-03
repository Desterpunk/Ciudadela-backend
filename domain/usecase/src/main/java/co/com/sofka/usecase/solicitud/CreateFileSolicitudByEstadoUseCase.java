package co.com.sofka.usecase.solicitud;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
public class CreateFileSolicitudByEstadoUseCase {

    private final FindAllSolicitudByEstadoUseCase findAllSolicitudByEstadoUseCase;

    public Mono<String> createFileSolicitudByEstado(String estado){
        return findAllSolicitudByEstadoUseCase.findAllSolicitudByEstado(estado).collectList().flatMap(resource -> {
            try {
                FileWriter myWriter = new FileWriter(estado + ".txt");
                myWriter.write("Solicitudes con estado " + estado + ": \n");
                myWriter.write(resource.toString());
                myWriter.close();
                return Mono.just("Successfully wrote to the file.");
            } catch (IOException e) {
                e.printStackTrace();
                return Mono.just("An error occurred.");
            }
        });
    }
}
