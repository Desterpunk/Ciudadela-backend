package co.com.sofka.scheduler;

import co.com.sofka.usecase.ordenConstruccion.UpdateOrdenStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStateSolicitud {
    private final UpdateOrdenStatusUseCase updateOrdenStatusUseCase;

    @Scheduled(cron = "0 0 7 1/1 * ?")
    public void validadetStateMorning(){
        updateOrdenStatusUseCase.updateOrdenStatus().subscribe();
    }

    @Scheduled(cron = "0 18 20 1/1 * ?")
    public void validadetStateNight(){
        updateOrdenStatusUseCase.updateOrdenStatus().subscribe();
    }
}
