
package state.game_character;

public class IntermediateState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        character.setExperiencePoints(
                character.getExperiencePoints() + 25);

        System.out.println("You trained!");
        System.out.println("You gained 25 XP.");
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println("You meditated!");
        character.setHealthPoints(
                Math.min(character.getHealthPoints() + 10, 100));
        System.out.println("You recovered health.");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("You fought!");
        character.setHealthPoints(
                Math.max(character.getHealthPoints() - 10, 0));
        System.out.println("You lost 10 health points.");
    }

    @Override
    public void displayAvailableActions() {
        System.out.println("1. Train");
        System.out.println("2. Meditate");
        System.out.println("3. Fight");
    }
}
