import { Injectable } from '@angular/core';
import { Storage, ref, getDownloadURL } from '@angular/fire/storage';
import { uploadBytes, list } from '@firebase/storage';

@Injectable({
  providedIn: 'root'
})

export class ImageService {
  url: string = ""
  constructor(private storage: Storage) { }

  uploadImage($event: any, name: string){
    const file = $event.target.file[0]
    const imgRef = ref(this.storage, `imagen/` + name)
    uploadBytes(imgRef, file)
    .then(response => {this.getImages()})
    .catch(error => console.log(error)
    )
  }

  getImages(){
    const imagesRef = ref(this.storage, 'imagen')
    list(imagesRef)
    .then(async response => {
      for(let item of response.items){
        this.url = await getDownloadURL(item);
        console.log("La URL es : " + this.url);
      }
    })
    .catch(error => console.log(error))
  }

}
