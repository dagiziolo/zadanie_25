package pl.ds.legoapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class LegoController {

    private LegoRepository legoRepository;

    public LegoController(LegoRepository legoRepository) {
        this.legoRepository = legoRepository;
    }

    @GetMapping("/")
    public String allLegos(Model model) {
        List<Lego> allLegos = legoRepository.findAll();
        model.addAttribute("legos", allLegos);
        return "lego";
    }

    @GetMapping("/szczegoly/{id}")
    public String legoDetails(@PathVariable Long id, Model model) {
        Optional<Lego> legoOptional = legoRepository.findById(id);
        Lego lego;

        if (legoOptional.isPresent()) {
            lego = legoOptional.get();
            model.addAttribute("lego", lego);
            return "details";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/dodaj")
    public String addLegoForm(Model model){
        model.addAttribute("lego", new Lego());
//        TargetAudience[] targetAudiences = TargetAudience.values();
        model.addAttribute("target", TargetAudience.values());
        return "addlegoform";
    }

    @PostMapping("/dodaj")
    public String postMovie(Lego lego) {
        legoRepository.save(lego);
        return "redirect:/";
    }

}
