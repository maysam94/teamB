
# TeamB - Opdrachtenbeheer Applicatie

Deze **TeamB** applicatie is een webapplicatie ontwikkeld met **Java Spring Boot**. Het biedt docenten een platform om opdrachten aan te maken, te beheren en te delen met studenten. De applicatie ondersteunt authenticatie en rolbeheer, zodat alleen bevoegde gebruikers (docenten) opdrachten kunnen toevoegen en beheren.

## Inhoudsopgave
1. [Overzicht](#overzicht)
2. [Functionaliteiten](#functionaliteiten)
3. [Installatie](#installatie)
4. [Gebruik](#gebruik)
    - [API Endpoints](#api-endpoints)
5. [Technologieën](#technologieën)
6. [Contributie](#contributie)
7. [Licentie](#licentie)

## Overzicht

Deze applicatie stelt docenten in staat om opdrachten toe te voegen, waarbij studenten later de opdrachten kunnen inzien en inleveren. De applicatie maakt gebruik van **Spring Boot** voor het backend-werk en biedt RESTful API's aan voor het beheren van opdrachten.

## Functionaliteiten

- **Opdrachten aanmaken**: Docenten kunnen nieuwe opdrachten aanmaken met details zoals titel en beschrijving.
- **Authenticatie en Autorisatie**: Gebruikers worden gecontroleerd op basis van hun rol (docent of student), zodat alleen docenten toegang hebben tot bepaalde functies.
- **Opdrachten beheren**: Gebruikers kunnen bestaande opdrachten bewerken of verwijderen.

## Installatie

Om het project lokaal op te zetten, volg je deze stappen:

1. Clone de repository:
   ```bash
   git clone https://github.com/maysam94/teamB.git
   ```

2. Navigeer naar de projectmap:
   ```bash
   cd teamB
   ```

3. Zorg ervoor dat **Java 11+** en **Maven** geïnstalleerd zijn.

4. Build het project met Maven:
   ```bash
   mvn clean install
   ```

5. Start de applicatie:
   ```bash
   mvn spring-boot:run
   ```

De applicatie draait nu op `http://localhost:8080/`.

## Gebruik

### API Endpoints

#### POST `/api/assignments`
- **Beschrijving:** Maakt een nieuwe opdracht aan.
- **Body:** JSON met opdrachtinformatie (titel, beschrijving).
  
  Voorbeeld:
  ```json
  {
    "title": "Nieuwe Opdracht",
    "description": "Maak een samenvatting van hoofdstuk 1."
  }
  ```

- **Authenticatie:** Vereist een geldige sessie-ID van een gebruiker met de rol **DOCENT**.

#### GET `/api/assignments`
- **Beschrijving:** Haalt een lijst op van alle opdrachten.

Voorbeeld request:
```bash
curl -X POST http://localhost:8080/api/assignments \
  -H "Content-Type: application/json" \
  -d '{"title": "Opdracht 1", "description": "Beschrijving van de opdracht."}'
```

## Technologieën

- **Java 11+**
- **Spring Boot** - Voor het backend-framework.
- **Maven** - Voor het bouwen en beheren van afhankelijkheden.
- **Hibernate** - Voor database-interactie.

## Contributie

Contributies zijn altijd welkom! Als je wilt bijdragen, volg dan de onderstaande stappen:

1. Fork deze repository.
2. Maak een nieuwe feature branch (`git checkout -b feature/nieuwe-functie`).
3. Commit je wijzigingen (`git commit -m 'Voeg nieuwe functie toe'`).
4. Push naar de branch (`git push origin feature/nieuwe-functie`).
5. Open een Pull Request.

## Licentie

Dit project is gelicenseerd onder de MIT-licentie. Zie het [LICENSE-bestand](LICENSE) voor details.

```

### Instructies:
1. Kopieer deze tekst.
2. Maak een nieuw bestand aan in je projectmap genaamd `README.md`.
3. Plak de tekst in dat bestand en sla het op.
4. Voeg het bestand toe aan je Git-repository met de volgende commando's:

```bash
git add README.md
git commit -m "Add README for TeamB project"
git push -u origin main
```

