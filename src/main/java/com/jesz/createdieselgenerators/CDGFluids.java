package com.jesz.createdieselgenerators;

import com.jesz.createdieselgenerators.content.concrete.ConcreteBucketItem;
import com.jesz.createdieselgenerators.content.concrete.ConcreteFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.world.item.DyeColor;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static com.jesz.createdieselgenerators.CreateDieselGenerators.REGISTRATE;

public class CDGFluids {

    public static final Map<DyeColor, FluidEntry<BaseFlowingFluid.Flowing>> CONCRETE = new HashMap<>();
    static {
        for (DyeColor color : DyeColor.values()) {
            CONCRETE.put(color,
                    REGISTRATE.fluid(color.getName() + "_cement", CreateDieselGenerators.rl("block/cement/" + color.getName() + "_still"), CreateDieselGenerators.rl("block/cement/" + color.getName() + "_flow"))
                            .lang(StringUtils.capitalize(color.getName()) + " Concrete")
                    .properties(b -> b.viscosity(1500)
                            .density(500))
                    .fluidProperties(p -> p.levelDecreasePerBlock(3)
                            .tickRate(12)
                            .slopeFindDistance(2)
                            .explosionResistance(100f)).source(p -> new ConcreteFluid(p, color))
                    .bucket((f, p) -> new ConcreteBucketItem(color, f, p))
                            .build()
                    .register()
            );
        }
    }

    public static void register() {}


}
