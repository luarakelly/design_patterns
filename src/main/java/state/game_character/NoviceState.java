package state.game_character;

public class NoviceState implements CharacterState {

    @Override
    public void train(GameCharacter character) {
        character.setExperiencePoints(
                character.getExperiencePoints() + 25);

        System.out.println("You trained!");
        System.out.println("You gained 25 XP.");

        if (character.getExperiencePoints() >= 100) {
            character.setState(new IntermediateState());
            System.out.println("You have reached Intermediate level!");
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        System.out.println("Novices cannot meditate yet.");
    }

    @Override
    public void fight(GameCharacter character) {
        System.out.println("Novices cannot fight yet.");
    }

    @Override
    public void displayAvailableActions() {
        System.out.println("1. Train");
    }

}
