package me.raynorjames.ai.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by productionaccount on 12/15/15.
 */
public class Primitives {
    public static class Flat {
        private List<Block> blocks = new ArrayList<>();
        public Flat(List<Block> blocks) {
            this.blocks = blocks;
        }

        public List<Block> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<Block> blocks) {
            this.blocks = blocks;
        }

        @Override
        public String toString() {
            return "Flat [" +
                    "blocks=" + blocks +
                    ']';
        }
    }

    public static class Cube {
        private List<Block> blocks = new ArrayList<>();

        public Cube(List<Block> blocks) {
            this.blocks = blocks;
        }

        public List<Block> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<Block> blocks) {
            this.blocks = blocks;
        }

        @Override
        public String toString() {
            return "Cube [" +
                    "blocks=" + blocks +
                    ']';
        }
    }

    public static class Column {
        private List<Block> blocks = new ArrayList<>();

        public Column(List<Block> blocks) {
            this.blocks = blocks;
        }

        public List<Block> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<Block> blocks) {
            this.blocks = blocks;
        }

        @Override
        public String toString() {
            return "Column [" +
                    "blocks=" + blocks +
                    ']';
        }
    }

    public static class Single {
        private Block block;

        public Single(Block block) {
            this.block = block;
        }

        public Block getBlock() {
            return block;
        }

        public void setBlock(Block block) {
            this.block = block;
        }

        @Override
        public String toString() {
            return "Single [" +
                    "block=" + block +
                    ']';
        }
    }
}
