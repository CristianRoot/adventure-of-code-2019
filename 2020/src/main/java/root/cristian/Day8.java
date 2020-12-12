package root.cristian;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 8: Handheld Halting ---
 */
public class Day8 {

    /**
     * Your flight to the major airline hub reaches cruising altitude without incident. While you consider checking the
     * in-flight menu for one of those drinks that come with a little umbrella, you are interrupted by the kid sitting
     * next to you.
     * <p/>
     * Their handheld game console won't turn on! They ask if you can take a look.
     * <p/>
     * You narrow the problem down to a strange infinite loop in the boot code (your puzzle input) of the device. You
     * should be able to fix it, but first you need to be able to run the code in isolation.
     * <p/>
     * The boot code is represented as a text file with one instruction per line of text. Each instruction consists of
     * an operation (acc, jmp, or nop) and an argument (a signed number like +4 or -20).
     * <p/>
     * acc increases or decreases a single global value called the accumulator by the value given in the argument. For
     * example, acc +7 would increase the accumulator by 7. The accumulator starts at 0. After an acc instruction, the
     * instruction immediately below it is executed next.
     * <p/>
     * jmp jumps to a new instruction relative to itself. The next
     * instruction to execute is found using the argument as an offset from the jmp instruction; for example, jmp +2
     * would skip the next instruction, jmp +1 would continue to the instruction immediately below it, and jmp -20
     * would cause the instruction 20 lines above to be executed next.
     * <p/>
     * nop stands for No OPeration - it does nothing. The instruction immediately below it is executed next.
     * For example, consider the following program:
     * <pre>
     * nop +0
     * acc +1
     * jmp +4
     * acc +3
     * jmp -3
     * acc -99
     * acc +1
     * jmp -4
     * acc +6
     * </pre>
     * These instructions are visited in this order:
     * <pre>
     * nop +0  | 1
     * acc +1  | 2, 8(!)
     * jmp +4  | 3
     * acc +3  | 6
     * jmp -3  | 7
     * acc -99 |
     * acc +1  | 4
     * jmp -4  | 5
     * acc +6  |
     * </pre>
     * First, the nop +0 does nothing. Then, the accumulator is increased from 0 to 1 (acc +1) and jmp +4 sets the next
     * instruction to the other acc +1 near the bottom. After it increases the accumulator from 1 to 2, jmp -4 executes,
     * setting the next instruction to the only acc +3. It sets the accumulator to 5, and jmp -3 causes the program to
     * continue back at the first acc +1.
     * <p/>
     * This is an infinite loop: with this sequence of jumps, the program will run forever. The moment the program tries
     * to run any instruction a second time, you know it will never terminate.
     * <p/>
     * Immediately before the program would run an instruction a second time, the value in the accumulator is 5.
     * <p/>
     * Run your copy of the boot code. Immediately before any instruction is executed a second time, what value is in
     * the accumulator?
     *
     * @param lines boot commands
     * @return accumulator value before starting the infinite loop
     */
    final long first(final List<String> lines) {
        final List<Integer> executedActions = new ArrayList<>();
        int pointer = 0;
        int acc = 0;

        while (!executedActions.contains(pointer)) {
            executedActions.add(pointer);

            final String[] codeLine = lines.get(pointer).split(" ");
            switch (codeLine[0]) {
                case "jmp":
                    pointer += Integer.parseInt(codeLine[1]);
                    break;
                case "acc":
                    acc += Integer.parseInt(codeLine[1]);
                case "nop":
                    pointer++;
                    break;
                default:
                    throw new IllegalStateException("Unknown operation");
            }
        }

        return acc;
    }

    final long second(final List<String> lines) {
        throw new IllegalStateException();
    }

}
