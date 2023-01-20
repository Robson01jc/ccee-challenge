import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable, zip } from 'rxjs';
import { FileUploadService } from '../file-upload.service';

@Component({
  selector: 'app-file-uploader',
  templateUrl: './file-uploader.component.html',
  styleUrls: ['./file-uploader.component.scss'],
})
export class FileUploaderComponent implements OnInit {
  loading = false;

  constructor(
    private snackBar: MatSnackBar,
    private fileUploadService: FileUploadService
  ) {}

  ngOnInit(): void {}

  showToast(message: string): void {
    this.snackBar.open(message, 'Dispensar', {
      duration: 5000,
    });
  }

  private removeAveragePrice(xmlDoc: Document): void {
    Array.from(xmlDoc.getElementsByTagName('precoMedio')).forEach((node) => {
      node.parentElement?.removeChild(node);
    });
  }

  private parseXml(file: File): Observable<Document> {
    return new Observable((subscriber) => {
      const reader = new FileReader();

      reader.onload = () => {
        const readXml = reader.result as string;
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(readXml, 'text/xml');

        subscriber.next(xmlDoc);
      };

      reader.onerror = () => {
        subscriber.error(reader.error);
      };

      reader.readAsText(file, 'UTF-8');
    });
  }

  private serializeXml(xmlDoc: Document, fileName: string): File {
    const serializer = new XMLSerializer();
    const xmlStr = serializer.serializeToString(xmlDoc);
    const parsedFile = new File(xmlStr.split('\n'), fileName, {
      type: 'text/xml',
    });

    return parsedFile;
  }

  private processFile(file: File): Observable<void> {
    return new Observable((subscriber) => {
      this.parseXml(file).subscribe({
        next: (xmlDoc) => {
          this.removeAveragePrice(xmlDoc);
          this.fileUploadService
            .uploadFile(this.serializeXml(xmlDoc, file.name))
            .subscribe({
              next: () => {
                this.showToast(`Arquivo '${file.name}' enviado com sucesso`);
                subscriber.next();
              },
              error: () => {
                this.showToast('something bad happened');
                subscriber.next();
              },
            });
        },
        error: () => {
          this.showToast('something bad happened');
          subscriber.next();
        },
      });
    });
  }

  readFiles(e: Event): void {
    this.loading = true;

    const files = Array.from((e.target as HTMLInputElement).files ?? []);
    zip(files.map((file) => this.processFile(file))).subscribe(() => {
      this.loading = false;
    });
  }
}
