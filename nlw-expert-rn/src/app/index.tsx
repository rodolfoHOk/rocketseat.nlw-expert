import { View } from 'react-native';
import { Header } from '@/components/header';
import { CategoryButton } from '@/components/category-button';

export default function Home() {
  return (
    <View className="flex-1 pt-4">
      <Header title="Faça seu pedido" cartQuantityItems={4} />

      <View className="flex-row gap-4 justify-between">
        <CategoryButton title="Lanche do dia" isSelected />
        <CategoryButton title="Promoções" />
        <CategoryButton title="Bebidas" />
      </View>
    </View>
  );
}
