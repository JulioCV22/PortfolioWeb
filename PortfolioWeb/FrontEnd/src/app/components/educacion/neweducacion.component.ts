import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Educacion } from 'src/app/model/educacion';
import { EducacionService } from 'src/app/service/educacion.service';

@Component({
  selector: 'app-neweducacion',
  templateUrl: './neweducacion.component.html',
  styleUrls: ['./neweducacion.component.css']
})
export class NeweducacionComponent  implements OnInit {
  public get router(): Router {
    return this.router;
  }
  public set router(value: Router) {
    this.router = value;
  }
  nombreE: string = '';
  descripcionE: string = '';

 
  constructor(private educacionS: EducacionService) { }
 
  ngOnInit(): void {
    
  }

  onCreate(): void{
    const educacion = new Educacion(this.nombreE, this.descripcionE);
    this.educacionS.save(educacion).subscribe(
      data=>{
        alert("Educacion añadida correctamente");
        this.router.navigate(['']);
      }, err =>{
        alert("Fallo");
        this.router.navigate(['']);
      }
    )
  }

}
