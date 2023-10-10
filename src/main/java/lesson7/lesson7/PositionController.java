package lesson7.lesson7;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Controller
public class PositionController {
    @NonNull
    private final AircraftRepository repository;
    /*private WebClient client = WebClient.create("http://localhost:7634/aircraft");*/

    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model){


     /*   client.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(p -> !p.getReg().isEmpty())
                .toStream()
                .forEach(d -> {
                    System.out.println(d);
                    repository.save(d);
                        }
                );
        Iterable<Aircraft> all = repository.findAll();
        System.out.println(all);*/
        model.addAttribute("currentPositions", repository.findAll());
        return "positions";
    }
}
