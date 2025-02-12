package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Categoria;
import co.usa.ciclo3.ciclo3.model.Papeleria;
import co.usa.ciclo3.ciclo3.repository.PapeleriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PapeleriaService {

    @Autowired
    private PapeleriaRepository papeleriaRepository;

    public List<Papeleria> getAll(){
        return papeleriaRepository.getAll();
    }

    public Optional<Papeleria> getPapeleria(int id){
        return papeleriaRepository.getPapeleria(id);
    }

    public Papeleria save(Papeleria p){
        if(p.getId()==null){
            return papeleriaRepository.save(p);
        }else{
            Optional<Papeleria> paux=papeleriaRepository.getPapeleria(p.getId());
            if(paux.isEmpty()){
                return papeleriaRepository.save(p);
            }else{
                return p;
            }
        }
    }

    public Papeleria update(Papeleria c){
        if(c.getId()!=null){
            Optional<Papeleria>g=papeleriaRepository.getPapeleria(c.getId());
            if(!g.isEmpty()){
                if(c.getName()!=null){
                    g.get().setName(c.getName());
                }
                if(c.getDescription()!=null){
                    g.get().setDescription(c.getDescription());
                }
                if(c.getPrice()!=null){
                    g.get().setPrice(c.getPrice());
                }
                return papeleriaRepository.save(g.get());
            }
        }
        return c;

    }

    public boolean deleteCategory(int id){
        Optional<Papeleria> c=getPapeleria(id);
        if(!c.isEmpty()){
            papeleriaRepository.delete(c.get());
            return true;
        }
        return false;

    }

}
