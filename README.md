# quize_converter
Small program written in java fx which purpose is to convert from old folder based structure to zipped json.

## Jak używać?
Jeśli chcesz podzielić się z rokiem testownikiem, który będzie można pobrać i rozwiązywać przez aplikację Quizee musisz podjąć następujące kroki.

1) Przekonwertować bazę ze starego formatu do paczki
2) Wysłać spakowaną paczkę na shadow.tesseract.studio@gmail.com

Aby dokonać konwersji pobierz [STĄD](https://github.com/ZbigniewTomanek/quize_converter/raw/master/out/artifacts/konwerter_jar/konwerter_jar.jar) konwerter, a następnie uruchom go klikając na niego dwa razy.

**Nie działa?**
Spróbuj pobrać i zainstalować ostatnią werjsę javy [STĄD](https://java.com/pl/download/)

W celu wygenerowania paczki z testem otwórz w programie folder testownika w starym formacie, a następnie uzupełnij wszystkie wymagane rybryki.

![alt text](https://raw.githubusercontent.com/ZbigniewTomanek/quize_converter/master/images/app.png)

**Ważne jest, aby wybrać z listy to samo kodowanie, co w plikach tekstowych zapisanych w folderze.**

Jeśli Twój testownik nie jest zapisany w jakiś egzotycznym formacie, to po kliknięciu "eksportuj" zostanie razem ze zdjęciami szybko przekształcony w paczkę zip, umieszczoną w folderze testu.

## Dlaczego zdecydowaliśmy się na zmianę formatu?

Dotychczas w komputerowych testownikach był wykorzystywany format testów wykorzystujący osobny plik .txt dla każdego pytania. 
Wygląda on tak, że pliki .txt opisujące pytania są wrzucone do folderu razem ze zdjęciami, wiązanymi do pytania/odpowiedzi po nazwie.

![alt text](https://raw.githubusercontent.com/ZbigniewTomanek/quize_converter/master/images/oldformat2.png)

Poprzez opis struktury pytania przez sekwencje białych znaków powstało wiele formatów, które bywają ze sobą niekompatybilne i są trudne w utrzymaniu.

Z powyższych powodów w mobilnej aplikacji testownika zdecydowaliśmy się przechowywać testy na dysku google w zipowanych paczkach, gdzie same pytania zawarte są w plku test.json, a metadane testu umożliwiające jego łatwą identyfikację w metadata.json.

Pliki te mają struktury zobrazowane na poniższych przykładach.

      test.json
![alt text](https://raw.githubusercontent.com/ZbigniewTomanek/quize_converter/master/images/question.png)

      metadata.json
![alt text](https://raw.githubusercontent.com/ZbigniewTomanek/quize_converter/master/images/metadata.png)

Zapewnia to dużo większą jednoznaczonosć i elastyczność.
