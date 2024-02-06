import { View } from 'react-native';
import { Header } from '@/components/header';

export default function Home() {
  return (
    <View className="flex-1 pt-4">
      <Header title="Faça seu pedido" cartQuantityItems={4} />
    </View>
  );
}
