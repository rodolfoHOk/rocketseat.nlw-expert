import { FlatList, View } from 'react-native';
import { Header } from '@/components/header';
import { CategoryButton } from '@/components/category-button';
import { CATEGORIES } from '@/utils/data/products';
import { useState } from 'react';

export default function Home() {
  const [category, setCategory] = useState<string>(CATEGORIES[0]);

  function handleCategorySelected(selectedCategory: string) {
    setCategory(selectedCategory);
  }

  return (
    <View className="flex-1 pt-4">
      <Header title="Faça seu pedido" cartQuantityItems={4} />

      <FlatList
        data={CATEGORIES}
        keyExtractor={(item) => item}
        renderItem={({ item }) => (
          <CategoryButton
            title={item}
            isSelected={item === category}
            onPress={() => handleCategorySelected(item)}
          />
        )}
        horizontal
        showsHorizontalScrollIndicator={false}
        className="max-h-10 mt-5"
        contentContainerStyle={{ gap: 12, paddingHorizontal: 20 }}
      />
    </View>
  );
}
